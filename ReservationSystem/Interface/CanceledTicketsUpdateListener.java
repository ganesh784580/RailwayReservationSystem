package Interface;

import railwayreservationsystem.Coach;
import railwayreservationsystem.UserInfo;

public interface CanceledTicketsUpdateListener {

    public void updateCanceledTickets(UserInfo DenyedPassengrInfo, Coach coach[]);

    public void updateRACs(UserInfo WaitingPassengrInfo,Coach coach[]);

}
