package railwayreservationsystem;

import Interface.IssueRequestHandler;
import Interface.PriorityHandler;
import Interface.RACTicketListener;
import Interface.RecordUdater;
import Interface.RequestListener;
import Interface.TicketCancellatiobListener;
import Interface.WaitingRequestListener;
import java.util.ArrayList;

public class ReservationManager implements RequestListener {

    private final int TotalNumberofTicket;
    private int AvailableTicket;
    private IssueRequestHandler IssueTicket;
    private int TotalReservedTicket;
    private int AvailablReservedTicket;
    private int TotalRACTicket;
    private int AvailablRACTicket;
    private int TotalCNFTicket;
    private int AvailablCNFTicket;
    private int NumberofRAC;
    private int NumberofWaitngTic;
    private PriorityHandler PrioHandler;
    private WaitingRequestListener WaitReqListener;
    private RecordUdater UpdateRecord;
    private TicketCancellatiobListener CancelationListener;
    private RACTicketListener RACTicListener;

    public ReservationManager(int NumberofCoach, IssueRequestHandler IssueTicket,
            PriorityHandler PrioHandler, WaitingRequestListener WaitReqListener,
            RecordUdater UpdateRecord, TicketCancellatiobListener CancelationListener,
            RACTicketListener RACTicListener) {

        TotalNumberofTicket = 9 * NumberofCoach;
        TotalCNFTicket = TotalNumberofTicket - 2 * NumberofCoach;
        AvailablCNFTicket = TotalCNFTicket;
        TotalRACTicket = 2 * NumberofCoach;
        AvailablRACTicket = TotalRACTicket;
        TotalReservedTicket = /*(20 * NumberofTicket) / 100;*/ TotalNumberofTicket;
        this.PrioHandler = PrioHandler;
        this.IssueTicket = IssueTicket;
        this.WaitReqListener = WaitReqListener;
        this.UpdateRecord = UpdateRecord;
        this.CancelationListener = CancelationListener;
        this.RACTicListener = RACTicListener;
    }

    @Override
    public Ticket bookTicket(Ticket ticket) {

        ArrayList<UserInfo> Users = ticket.getInfo();
        for (int usercount = 0; usercount < Users.size(); ++usercount) {
            Users.get(usercount).setPNR(ticket.getPNR());
            if (AvailablCNFTicket > 0) {
                if (AvailablCNFTicket >/*= ReservedTicket*/ 0) {
                    IssueTicket.issueTicket(Users.get(usercount));
                    --AvailablCNFTicket;
                } else {
                    PrioHandler.implementPriority(ticket);
                }
            } else if (AvailablRACTicket > 0) {
                IssueTicket.issueRAC(Users.get(usercount));
                RACTicListener.addPassengr(Users.get(usercount));
                --AvailablRACTicket;
                ++NumberofRAC;
            } else {
                WaitReqListener.handleRequest(Users.get(usercount));
                Users.get(usercount).setStatus(Status.WATNG);
                Users.get(usercount).setWaitingNumber();
                ++NumberofWaitngTic;
            }
        }
        UpdateRecord.addTicket(ticket);
        return null;
    }

    @Override
    public Ticket cancelTicket(int PNR) {
        Ticket ticket = UpdateRecord.cancelTicket(PNR);
        if (ticket != null) {
            setTickets(ticket);
            CancelationListener.allocateCanceledBerth(ticket);
        }
        return ticket;
    }

    private void setTickets(Ticket ticket) {
        int RACCount = 0, CNFCount = 0;
        ArrayList<UserInfo> Users = ticket.getInfo();
        for (int usercount = 0; usercount < Users.size(); ++usercount) {
            if (Users.get(usercount).getStatus().equals(Status.CNF)) {
                ++CNFCount;
            } else if (Users.get(usercount).getStatus().equals(Status.RAC)) {
                ++RACCount;
            }
        }
        int CurentRACCount = RACTicListener.getNumberofPassengerforBerth() - RACCount;
        int CurentWLCount = WaitReqListener.getNumberofPassengerforBerth();

        if (CurentRACCount == 0 && CurentWLCount == 0) {
            AvailablCNFTicket = AvailablCNFTicket + CNFCount;
            AvailablRACTicket = AvailablRACTicket + RACCount;
        } else if ((CNFCount - CurentRACCount) <= 0) {
            if (CurentWLCount == 0) {
                AvailablRACTicket = AvailablRACTicket + CNFCount + RACCount;
            } else {
                if ((CNFCount - CurentWLCount) > 0) {
                    AvailablRACTicket = AvailablRACTicket + CNFCount + RACCount - CurentWLCount;
                }
            }
        } else if ((CNFCount - CurentRACCount) > 0) {
            if (CurentWLCount == 0) {
                AvailablCNFTicket = AvailablCNFTicket + CNFCount - CurentRACCount;
                AvailablRACTicket = AvailablRACTicket + CurentRACCount + RACCount;
            } else {
                if ((CNFCount - CurentWLCount - CurentRACCount) > 0) {
                    AvailablCNFTicket = AvailablCNFTicket + CNFCount - CurentRACCount - CurentWLCount;
                    AvailablRACTicket = AvailablRACTicket + CurentRACCount + RACCount;
                } else {
                    int RemaingTick = Math.abs(CNFCount - CurentWLCount - CurentRACCount);
                    if (CurentRACCount - RemaingTick > 0) {
                        AvailablRACTicket = AvailablRACTicket + CurentRACCount - RemaingTick + RACCount;
                    }

                }
            }
        }

    }
}
