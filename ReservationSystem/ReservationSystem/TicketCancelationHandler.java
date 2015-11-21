package railwayreservationsystem;

import Interface.CanceledTicketsUpdateListener;
import Interface.IssueRequestHandler;
import Interface.RACTicketListener;
import Interface.TicketCancellatiobListener;
import Interface.WaitingRecordAccessLayer;
import java.util.ArrayList;

public class TicketCancelationHandler implements TicketCancellatiobListener {

    private RACTicketListener RACList;
    private WaitingRecordAccessLayer WaitingList;
    private CanceledTicketsUpdateListener CancelsUpdatListnr;
    private Coach[] coach;
    IssueRequestHandler IssueTicket;

    public TicketCancelationHandler(Coach[] coach, RACTicketListener RACList,
            WaitingRecordAccessLayer WaitingList, CanceledTicketsUpdateListener CancelsUpdatListnr,
            IssueRequestHandler IssueTicket) {
        this.coach = coach;
        this.RACList = RACList;
        this.WaitingList = WaitingList;
        this.CancelsUpdatListnr = CancelsUpdatListnr;
        this.IssueTicket=IssueTicket;
    }

    @Override
    public void allocateCanceledBerth(Ticket CanceledTicket) {
        ArrayList<UserInfo> DenyedPassengr = CanceledTicket.getInfo();
        UserInfo DenyedPassengrInfo;
        UserInfo WaitingPassengrInfo;
        for (int i = 0; i < DenyedPassengr.size(); ++i) {
            DenyedPassengrInfo = DenyedPassengr.get(i);
            if (DenyedPassengrInfo.getStatus() != Status.WATNG) {
                WaitingPassengrInfo = getWaitingPasngr();
                while (WaitingPassengrInfo != null && DenyedPassengrInfo.getPNR() == WaitingPassengrInfo.getPNR()) {
                    WaitingPassengrInfo = getWaitingPasngr();
                }
                if (WaitingPassengrInfo != null) {
                    WaitingPassengrInfo.setCoachNum(DenyedPassengrInfo.getCoachNum());
                    WaitingPassengrInfo.setSeatNumber(DenyedPassengrInfo.getSeatNumber());
                    WaitingPassengrInfo.setBerthPreference(DenyedPassengrInfo.getBerthPreference());
                    WaitingPassengrInfo.setStatus(DenyedPassengrInfo.getStatus());
                } else {
                    CancelsUpdatListnr.updateCanceledTickets(DenyedPassengrInfo, coach);
                }
            }
        }
        int Temp_TotalRACTickets = 2 * coach.length;
        int AvailableRACS = Temp_TotalRACTickets - RACList.getNumberofPassengerforBerth();
        if (AvailableRACS > 0 & WaitingList.getNumberofPassforBerth() > 0) {
            for (int i = 0; i < AvailableRACS && WaitingList.getNumberofPassforBerth() != 0; ++i) {
                WaitingPassengrInfo = WaitingList.removeTicket();
                RACList.addPassengr(WaitingPassengrInfo);
               IssueTicket.issueRAC(WaitingPassengrInfo);  

            }
        }

    }

    private UserInfo getWaitingPasngr() {
        if (RACList.getNumberofPassengerforBerth() > 0 || WaitingList.getNumberofPassforBerth() > 0) {
            UserInfo WaitingPassengrInfo = RACList.removeTicket();
            if (WaitingPassengrInfo != null) {
                CancelsUpdatListnr.updateRACs(WaitingPassengrInfo, coach);
                return WaitingPassengrInfo;
            } else {
                return WaitingList.removeTicket();
            }
        }
        return null;
    }

}
