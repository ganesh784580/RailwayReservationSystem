package railwayreservationsystem;

import Database.RACTicketsRecord;
import Database.TicketBookingRecord;
import Database.WaitingListRecord;
import Interface.RequestListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RailwayReservationSystem {

    private RequestListener Listener;
    private RecordManager recordmangaer;
    private WaitingRequestHandler WRequestHandler;
    private Coach coach[];
    private TicketBookingRecord TBokingRecord;
    private WaitingListRecord WListRecord;
    private RACTicketsRecord RACRecord;
    private RACTicketHandler RACTicHandler;
    private TicketIssuer TicketIssuer;

    public RailwayReservationSystem(int CoachesCount) {
        coach = new Coach[CoachesCount];
        for (int i = 0; i < CoachesCount; ++i) {
            coach[i] = new Coach();
        }
        TBokingRecord = new TicketBookingRecord();
        WListRecord = new WaitingListRecord();
        RACRecord = new RACTicketsRecord();

        recordmangaer = new RecordManager(TBokingRecord);
        WRequestHandler = new WaitingRequestHandler(WListRecord);
        RACTicHandler = new RACTicketHandler(RACRecord);
        TicketIssuer = new TicketIssuer(CoachesCount, coach);
        Listener = new ReservationManager(CoachesCount, TicketIssuer,
                new PriorityImplimenter(), WRequestHandler, recordmangaer,
                new TicketCancelationHandler(coach, RACTicHandler, WListRecord, new CanceledTicketsUpdater(), TicketIssuer),
                RACTicHandler);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter th enumber of coaches, should be >= 1");
        int Coaches = Integer.parseInt(Input.readLine());
        while (!(Coaches >= 1)) {
            System.out.println("please give input with in the range is >= 1");
            Coaches = Integer.parseInt(Input.readLine());
        }
        boolean Exit = false;
        UserInfo Info;
        ArrayList<UserInfo> RequestedTick;
        RailwayReservationSystem RRS = new RailwayReservationSystem(Coaches);

        while (!Exit) {
            System.out.println("1.) Enter 1 for booking Tickets\n"
                    + "2.) Enter 2 for Booking History\n"
                    + "3.) Enter 3 for Canceling Ticket\n"
                    + "4.) Enter 4 for Waiting List\n"
                    + "5.) Enter 5 for Exit\n");
            int Choice = Integer.parseInt(Input.readLine());
            switch (Choice) {
                case 1:
                    System.out.println("Enter the number of tickets should be less than or equal to 4");
                    int TicketCount = Integer.parseInt(Input.readLine());
                    while (TicketCount > 4) {
                        System.out.println("please give input with in the range is less than or equal to 4");
                        TicketCount = Integer.parseInt(Input.readLine());
                    }
                    Info = new UserInfo();
                    RequestedTick = new ArrayList<>();
                    while (TicketCount > 0) {
                        RequestedTick.add(Info.getInfo(Input));
                        Info = new UserInfo();
                        --TicketCount;
                    }
                    Ticket ticket = new Ticket(RequestedTick);
                    RRS.Listener.bookTicket(ticket);

                    RequestedTick = ticket.getInfo();

                    for (int i = 0; i < RequestedTick.size(); ++i) {
                        Info = RequestedTick.get(i);
                        System.out.println("your PNR number is " + Info.getPNR()
                                + " \n and status is " + Info.getStatus()
                                + "\n and seat number is " + Info.getSeatNumber()
                                + "\n alloted Berth is " + Info.getBerthPreference());
                    }
                    break;
                case 2:
                    RRS.recordmangaer.showBookingRecord();
                    break;
                case 3:
                    System.out.println("Enter ticket PNR Number");
                    int PNRnum = Integer.parseInt(Input.readLine());
                    ticket = RRS.Listener.cancelTicket(PNRnum);
                    if (ticket != null) {
                        System.out.println("Ticket PNR number" + ticket.getPNR() + " is canceled");
                    } else {
                        System.out.println("Ticket PNR number " + PNRnum + " not found");
                    }
                    break;
                case 4:
                    RRS.WRequestHandler.showWaitingList();
                    break;
                case 5:
                    Exit = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println("Thanks for using Railway Reservation System ");
                    break;
                default:
                    System.out.println("That's a invalid input");
                    break;
            }

        }
    }

}
