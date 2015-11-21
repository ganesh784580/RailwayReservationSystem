package Interface;

import java.util.LinkedList;
import railwayreservationsystem.UserInfo;

public interface WaitingRecordAccessLayer {

    public LinkedList<UserInfo> getWaitingList();

    public void addTicket(UserInfo Info);

    public LinkedList<UserInfo> getWaitingRecord();

    public UserInfo removeTicket();

    public int getNumberofPassforBerth();

}
