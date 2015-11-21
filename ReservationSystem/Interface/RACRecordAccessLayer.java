
package Interface;


import railwayreservationsystem.UserInfo;

public interface RACRecordAccessLayer {
    
    public void addTicket(UserInfo Info);

    
    public UserInfo removeTicket() ;

    public int getNumberofPassengerforBerth();
    
}
