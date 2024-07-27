package trainTicketBooking2;

public class TicketCanceling {
    private int pnr;
    private int seats;
    private TicketSystem ticketSystem;

    TicketCanceling(int pnr, int seats) {
        this.pnr = pnr;
        this.seats = seats;
        this.ticketSystem = TicketSystem.getInstance();
    }

    private void cancelTicket() {
        Ticket ticket = ticketSystem.getTicket(pnr);

        if(ticket != null) {
            int bookedSeats = ticket.getSeats();
            char source = ticket.getSource(), destination = ticket.getDestination();
            //partial cancellation
            if(bookedSeats > seats) {
                ticket.setSeats(bookedSeats - seats);
                ticketSystem.storePartiallyCanceledSeats(pnr, seats); // stores partially cancelled seats
                System.out.println("Partially cancelled pnr "+ pnr);
            }
            else {
                ticketSystem.processCancellation(pnr, ticket);
                System.out.println("Cancelled Ticket pnr "+ pnr);
            }
            ticketSystem.increaseSeatAvailability(source, destination, seats); // update how many seats are free
            WaitingListManager waitingListManager = new WaitingListManager();
            waitingListManager.processWaitingList(); // check whether we can pick a ticket from waiting list
        }
        else {
            System.out.println("Ticket with pnr "+pnr+" not found");
        }
    }

    protected void execute() {
        this.cancelTicket();
    }
}
