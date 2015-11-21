
package Interface;

import railwayreservationsystem.Ticket;
import railwayreservationsystem.UserInfo;


public interface IssueRequestHandler {
    Ticket issueTicket(UserInfo Info);

    public void issueRAC(UserInfo Info);
}
