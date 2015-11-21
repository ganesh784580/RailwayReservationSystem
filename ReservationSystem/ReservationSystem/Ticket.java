package railwayreservationsystem;

import java.util.ArrayList;

public class Ticket {

    private static int PNRCount = 0;
    private final int PNR = ++PNRCount;
    private ArrayList<UserInfo> Info;
    
    public Ticket(ArrayList<UserInfo> Info) {
        this.Info = Info;
    }

    public int getPNR() {
        return PNR;
    }

    public ArrayList<UserInfo> getInfo() {
        return Info;
    }

    int getRequestedTicke() {
        return Info.size();
    }

    
}
