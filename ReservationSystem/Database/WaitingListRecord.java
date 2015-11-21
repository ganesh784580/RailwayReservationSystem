package Database;

import Interface.WaitingRecordAccessLayer;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import railwayreservationsystem.UserInfo;

public class WaitingListRecord implements WaitingRecordAccessLayer {

    private LinkedList<UserInfo> WaitingList;

    public WaitingListRecord() {
        WaitingList = new LinkedList<>();
    }

    @Override
    public LinkedList<UserInfo> getWaitingList() {
        return WaitingList;
    }

    @Override
    public void addTicket(UserInfo Info) {

        WaitingList.add(Info);
    }

    @Override
    public LinkedList<UserInfo> getWaitingRecord() {
        return WaitingList;
    }

    @Override
    public UserInfo removeTicket() {
        try {
            return WaitingList.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getNumberofPassforBerth() {
        return WaitingList.size();
    }

}
