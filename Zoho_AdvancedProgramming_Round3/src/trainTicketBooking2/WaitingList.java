package trainTicketBooking2;

public class WaitingList {
    private char source;
    private char destination;
    private int seats;
    private TicketSystem ticketSystem;

    WaitingList(char source, char destination, int seats) {
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.ticketSystem = TicketSystem.getInstance();
    }

    private void addToWaitingList() {
        Ticket ticket = new Ticket(source, destination, seats, TicketStatus.WaitingList);
        int pnrNumber = ticket.getPnrNumber();
        ticketSystem.waitingList.put(pnrNumber, ticket);
        ticketSystem.seatsBooked += seats;

        System.out.println("Added to Waiting List with pnr number "+pnrNumber);
    }

    protected void execute() {
        this.addToWaitingList();
    }
}