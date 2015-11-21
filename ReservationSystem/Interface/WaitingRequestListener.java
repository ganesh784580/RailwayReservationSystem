package Interface;

import railwayreservationsystem.UserInfo;

public interface WaitingRequestListener {

    public void handleRequest(UserInfo Info);

    public int getNumberofPassengerforBerth();

}
