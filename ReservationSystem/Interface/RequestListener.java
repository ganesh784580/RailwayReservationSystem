
package Interface;

import railwayreservationsystem.Ticket;


public interface RequestListener {
    Ticket bookTicket(Ticket ticket);
    Ticket cancelTicket(int PNR); 
     
}
