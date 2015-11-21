package railwayreservationsystem;


import Interface.WaitingRecordAccessLayer;
import Interface.WaitingRequestListener;
import java.util.Iterator;
import java.util.LinkedList;

public class WaitingRequestHandler implements WaitingRequestListener {

    private WaitingRecordAccessLayer AccessLayer;

    public WaitingRequestHandler(WaitingRecordAccessLayer AccessLayer) {
        this.AccessLayer = AccessLayer;
    }

    @Override
    public void handleRequest(UserInfo Info) {
        AccessLayer.addTicket(Info);
    }

    void showWaitingList() {
        LinkedList<UserInfo> WaitingList = AccessLayer.getWaitingRecord();
        if (!WaitingList.isEmpty()) {
            Iterator<UserInfo> WaitingListIterator = WaitingList.listIterator();
            System.out.println("PNR           Status      Waiting Number");
            while (!WaitingListIterator.hasNext()) {
                UserInfo Info = WaitingListIterator.next();
                //Users = ticket.getInfo();
                //for (int User = 0; User < Users.size(); ++User) {
                System.out.println(Info.getPNR() + "        "
                        + Info.getStatus() + "        " + Info.getWaitingNumber());
                //}
            }
        } else {
            System.out.println("No waiting Record Found");
        }
    }

    @Override
    public int getNumberofPassengerforBerth() {
        return AccessLayer.getNumberofPassforBerth();
    }
}
