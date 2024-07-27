package trainTicketBooking2;

public class WaitingListManager {
    private TicketSystem ticketSystem;

    WaitingListManager() {
        this.ticketSystem = TicketSystem.getInstance();
    }

    // Handles Waiting List to Booking List process
    protected void processWaitingList() {
        for(Ticket waiting : ticketSystem.waitingList.values()) {
            boolean isValid;
            char source = waiting.getSource(), destination = waiting.getDestination();
            int seats = waiting.getSeats();

            // seat availability check (method re-use)
            isValid = ticketSystem.checkSeatAvailability(source, destination, seats);

            if(isValid) {
                ticketSystem.decreaseSeatAvailability(source, destination, seats); // update seatsAvailable array
                ticketSystem.setSeatsBooked(ticketSystem.getSeatsBooked()-seats); // update seatsBooked on waiting list
                updateTicketToBookingList(waiting);
            }
        }
    }

    private void updateTicketToBookingList(Ticket waiting) {
        int pnrNumber = waiting.getPnrNumber();
        waiting.setTicketStatus(TicketStatus.Booked); // status from waiting list to booked
        ticketSystem.addToBookedTickets(pnrNumber, waiting); // add waiting to booked (method re-use)
        ticketSystem.waitingList.remove(pnrNumber); // removing from waiting list queue (This is why we used ConcurrentHashMap)
        System.out.println("Booking Confirmed for pnr number "+pnrNumber);
    }

    protected void waitingListEntry(char source, char destination, int seats) {
        WaitingList wl = new WaitingList(source, destination, seats);
        wl.execute();
    }
}
