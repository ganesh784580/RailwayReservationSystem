package railwayreservationsystem;

import Interface.CanceledTicketsUpdateListener;

public class CanceledTicketsUpdater implements CanceledTicketsUpdateListener {

    /**
     *
     * @param DenyedPassengrInfo
     * @param coach
     */
    @Override
    public void updateCanceledTickets(UserInfo DenyedPassengrInfo, Coach coach[]) {

        if (DenyedPassengrInfo.getBerthPreference().equals(Berth.LB.toString())) {

            coach[DenyedPassengrInfo.getCoachNum()].freeLBBerth(DenyedPassengrInfo.getSeatIndex(),
                    DenyedPassengrInfo.getSeatNumber());

        } else if (DenyedPassengrInfo.getBerthPreference().equals(Berth.MB.toString())) {

            coach[DenyedPassengrInfo.getCoachNum()].freeMBBerth(DenyedPassengrInfo.getSeatIndex(),
                    DenyedPassengrInfo.getSeatNumber());

        } else if (DenyedPassengrInfo.getBerthPreference().equals(Berth.UB.toString())) {

            coach[DenyedPassengrInfo.getCoachNum()].freeUBBerth(DenyedPassengrInfo.getSeatIndex(),
                    DenyedPassengrInfo.getSeatNumber());

        } else if (DenyedPassengrInfo.getBerthPreference().equals(Berth.SUB.toString())) {

            coach[DenyedPassengrInfo.getCoachNum()].freeSUBBerth(DenyedPassengrInfo.getSeatNumber());

        } else if (DenyedPassengrInfo.getBerthPreference().equals(Berth.SLB.toString())) {

            coach[DenyedPassengrInfo.getCoachNum()].freeSLBBerth(DenyedPassengrInfo.getSeatIndex(),
                    DenyedPassengrInfo.getSeatNumber());
        }

    }

    @Override
    public void updateRACs(UserInfo WaitingPassengrInfo, Coach coach[]) {

        coach[WaitingPassengrInfo.getCoachNum()].freeSLBBerth(WaitingPassengrInfo.getSeatIndex(),
                WaitingPassengrInfo.getSeatNumber());

    }
}
