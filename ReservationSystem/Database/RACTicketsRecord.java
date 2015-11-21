package Database;


import Interface.RACRecordAccessLayer;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import railwayreservationsystem.UserInfo;

public class RACTicketsRecord implements RACRecordAccessLayer{

    private LinkedList<UserInfo> RACList=new LinkedList<>();

    @Override
    public void addTicket(UserInfo Info) {
        RACList.add(Info);
    }

    @Override
    public UserInfo removeTicket() {
        try {
        return RACList.removeFirst();     
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
      
    }

    @Override
    public int getNumberofPassengerforBerth() {
        return RACList.size();
    }
}
