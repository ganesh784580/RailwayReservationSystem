package Interface;

import java.util.HashMap;
import railwayreservationsystem.Ticket;

public interface BookingRecordAccessLayer {

    public void addTicket(Ticket ticket);

    public HashMap<Integer, Ticket> getRecord();

    public Ticket cancelTicket(int PNR);

}
