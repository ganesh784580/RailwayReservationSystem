
package Interface;

import railwayreservationsystem.UserInfo;


public interface RACTicketListener {

    public void addPassengr(UserInfo get);

    public int getNumberofPassengerforBerth();

    public UserInfo removeTicket();
    
}
