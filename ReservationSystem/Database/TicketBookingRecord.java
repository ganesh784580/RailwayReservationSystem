package Database;

import Interface.BookingRecordAccessLayer;

import java.util.HashMap;
import railwayreservationsystem.Ticket;

public class TicketBookingRecord implements BookingRecordAccessLayer {

    private HashMap<Integer, Ticket> TicketRecord;

    public TicketBookingRecord() {
        TicketRecord = new HashMap<>();
    }

    @Override
    public void addTicket(Ticket ticket) {
        TicketRecord.put(ticket.getPNR(), ticket);
    }

    @Override
    public HashMap<Integer, Ticket> getRecord() {
        return TicketRecord;
    }

    @Override
    public Ticket cancelTicket(int PNR) {
        return TicketRecord.remove(PNR);
    }

}
