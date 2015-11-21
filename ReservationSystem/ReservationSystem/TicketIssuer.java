package railwayreservationsystem;

import Interface.IssueRequestHandler;

public class TicketIssuer implements IssueRequestHandler {

    private int CoachNumber;
    private Coach coach[];
    private int TotalLB;
    private int TotalMB;
    private int TotalUB;
    private int TotalSLB;
    private int TotalSUB;

    public TicketIssuer(int CoachNumber,Coach coach[]) {
        this.CoachNumber = CoachNumber;
        this.coach=coach;
        assignBerthNumber();
        TotalLB = 2 * CoachNumber;
        TotalMB = 2 * CoachNumber;
        TotalUB = 2 * CoachNumber;
        TotalSLB = 2 * CoachNumber;
        TotalSUB = CoachNumber;
    }

    @Override
    public Ticket issueTicket(UserInfo Info) {
        UserInfo user = Info;
        for (int coachcount = 0; coachcount < coach.length; ++coachcount) {
            if (user.getBerthPreference().equals("LB") && coach[coachcount].getAvailNumberofLB() > 0) {
                user.setSeatNumber(coach[coachcount].getLB());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.CNF);
                --TotalLB;
                user.setCoachNum(coachcount);
                /*System.out.println("current coach number " + coachcount + " and LB count " + coach[coachcount].getAvailNumberofLB()
                 + " alloted Berth number " + user.getSeatNumber());*/
                break;
            } else if (user.getBerthPreference().equals("MB") && coach[coachcount].getAvailNumberofMB() > 0) {
                user.setSeatNumber(coach[coachcount].getMB());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.CNF);
                --TotalMB;
                user.setCoachNum(coachcount);
                break;
            } else if (user.getBerthPreference().equals("UB") && coach[coachcount].getAvailNumberofUB() > 0) {
                user.setSeatNumber(coach[coachcount].getUB());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.CNF);
                user.setCoachNum(coachcount);
                --TotalUB;
                break;
            } else if (isPreferNotAvail(user)) {
                handleOhterTickets(user);
            }
        }

        return null;
    }

    private void assignBerthNumber() {
        int BerthNumber = 1;
        for (int i = 0; i < coach.length; ++i) {
            coach[i].setLBNumber(BerthNumber, BerthNumber + 3);
            ++BerthNumber;
            coach[i].setMBNumber(BerthNumber, BerthNumber + 3);
            ++BerthNumber;
            coach[i].setUBNumber(BerthNumber, BerthNumber + 3);
            BerthNumber = BerthNumber + 4;
            coach[i].setSLSeatNumber(BerthNumber, BerthNumber + 1);
            BerthNumber = BerthNumber + 2;
            coach[i].setSUBNumber(BerthNumber);
            ++BerthNumber;
        }

    }

    private void handleOhterTickets(UserInfo user) {
        for (int coachcount = 0; coachcount < coach.length; ++coachcount) {
            if (coach[coachcount].getAvailNumberofUB() > 0) {
                user.setSeatNumber(coach[coachcount].getUB());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.CNF);
                user.setCoachNum(coachcount);
                user.setBerthPreference(Berth.UB.toString());
            } else if (coach[coachcount].getAvailNumberofMB() > 0) {
                user.setSeatNumber(coach[coachcount].getMB());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.CNF);
                user.setBerthPreference(Berth.MB.toString());
                user.setCoachNum(coachcount);

            } else if (coach[coachcount].getAvailNumberofSUB() > 0 ) {
                user.setSeatNumber(coach[coachcount].getSUBNumber());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.CNF);
                user.setBerthPreference(Berth.SUB.toString());
                user.setCoachNum(coachcount);
            } else {
                user.setSeatNumber(coach[coachcount].getLB());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.CNF);
                user.setCoachNum(coachcount);
                user.setBerthPreference(Berth.LB.toString());
            }
        }
    }

    private boolean isPreferNotAvail(UserInfo user) {
        if (user.getBerthPreference().equals(Berth.LB.toString()) && TotalLB <= 0) {
            return true;
        } else if (user.getBerthPreference().equals(Berth.UB.toString()) && TotalUB <= 0) {
            return true;
        } else if (user.getBerthPreference().equals(Berth.MB.toString()) && TotalMB <= 0) {
            return true;
        } else if (user.getBerthPreference().equals(Berth.SUB.toString()) && TotalSUB <= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void issueRAC(UserInfo user) {

        for (int coachcount = 0; coachcount < coach.length; ++coachcount) {
            if (coach[coachcount].getAvailNumberofSLSeat() > 0) {
                user.setSeatNumber(coach[coachcount].getSLSeat());
                user.setSeatIndex(coach[coachcount].getSeatIndex());
                coach[coachcount].setSeatIndex();
                user.setStatus(Status.RAC);
                user.setCoachNum(coachcount);
                user.setBerthPreference(Berth.SLB.toString());
            }
        }
    }
}
