package trainTicketBooking2;

public class Ticket {
    private static int pnrGenerator = 1;

    private final int pnrNumber = pnrGenerator++;
    private final char source;
    private final char destination;
    private int seats;
    private TicketStatus ticketStatus;

    Ticket(char source, char destination, int seats, TicketStatus ticketStatus) {
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.ticketStatus = ticketStatus;
    }

    public char getSource() {
        return source;
    }

    public char getDestination() {
        return destination;
    }

    public int getSeats() {
        return seats;
    }

    public int getPnrNumber() {
        return pnrNumber;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "pnrNumber=" + pnrNumber +
                ", source=" + source +
                ", destination=" + destination +
                ", seats=" + seats +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
