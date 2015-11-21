package railwayreservationsystem;

import Interface.BookingRecordAccessLayer;
import Interface.RecordUdater;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class RecordManager implements RecordUdater {

    private BookingRecordAccessLayer AccessLayer;

    public RecordManager(BookingRecordAccessLayer AccessLayer) {
        this.AccessLayer = AccessLayer;
    }

    @Override
    public void addTicket(Ticket ticket) {
        AccessLayer.addTicket(ticket);
    }

    void showBookingRecord() {
        HashMap<Integer, Ticket> TicketRecord = AccessLayer.getRecord();
        if (!TicketRecord.isEmpty()) {
            Set ticketset = TicketRecord.keySet();
            Iterator<Integer> iter = ticketset.iterator();
            ArrayList<UserInfo> Users;
            System.out.println("PNR      Name      SeatNumber      Status      BerthType");
            while (iter.hasNext()) {
                int PNRNum = iter.next();
                Ticket ticket=TicketRecord.get(PNRNum);
                Users = ticket.getInfo();
                for (int User = 0; User < Users.size(); ++User) {
                    UserInfo Info = Users.get(User);
                    System.out.println(Info.getPNR() + "        " + Info.getName() + "         "
                            + Info.getSeatNumber() + "              " + Info.getStatus() + "          " + 
                            Info.getBerthPreference() );
                }
            }
        } else {
            System.out.println("Record not found");
        }
    }

    @Override
    public Ticket cancelTicket(int PNR) {
      return AccessLayer.cancelTicket(PNR);
    }

}
