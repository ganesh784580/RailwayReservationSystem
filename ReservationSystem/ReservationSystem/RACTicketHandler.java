

package railwayreservationsystem;

import Interface.RACRecordAccessLayer;
import Interface.RACTicketListener;


public class RACTicketHandler implements RACTicketListener{
private RACRecordAccessLayer RACList;

    public RACTicketHandler(RACRecordAccessLayer RACList) {
        this.RACList=RACList;
    }
    
    @Override
    public void addPassengr(UserInfo Info) {
        RACList.addTicket(Info); 
    }

    @Override
    public int getNumberofPassengerforBerth() {
        return RACList.getNumberofPassengerforBerth();
    }

    @Override
    public UserInfo removeTicket() {
        return RACList.removeTicket();
    }
    
}
