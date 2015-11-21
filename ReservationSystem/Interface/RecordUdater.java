

package Interface;

import railwayreservationsystem.Ticket;

public interface RecordUdater {

    public void addTicket(Ticket ticket);

    public Ticket cancelTicket(int PNR);
    
}
