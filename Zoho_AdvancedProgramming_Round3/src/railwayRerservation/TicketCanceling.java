package railwayRerservation;

import java.util.*;

public class TicketCanceling extends TicketBooking
{
	//Only for RAC
	private static char preferenceTracker = '\0';
	private static int canceledSeatNumber = 0;
	
	private static Map<Integer,Character> seatNumberWithBerth = new HashMap<Integer,Character>();
	
	public static String canceling(int id)
	{
		for(Passenger p : confirmedList)
		{
			if(p.getId() == id) {
				cancel(p);
				return "Success";
			}
		}
		
		for(Passenger p : racQueue)
		{
			if(p.getId() == id) {
				cancel(p);
				return "Success";
			}
		}
		
		for(Passenger p : waitingQueue)
		{
			if(p.getId() == id) {
				cancel(p);
				return "Success";
			}
		}
		
		return "Invalid Id";
	}

	private static void cancel(Passenger p) 
	{
		if(p.getTicketType() == "berth")
		{
			//Only for RAC
			preferenceTracker = p.getPreference();
			canceledSeatNumber = p.getSeatNumber();
			//Map for reference in future
			seatNumberWithBerth.put(canceledSeatNumber,preferenceTracker);
			
			deleteFromAllLists(p);
			addRacToBerth(racQueue.poll());
			addWaitingToRac(waitingQueue.poll());
		}
		else if(p.getTicketType() == "rac")
		{
			racQueue.remove(p);
			addWaitingToRac(waitingQueue.poll());
		}
		else
		{
			waitingQueue.remove(p);
		}
	}

	private static void addWaitingToRac(Passenger p) {
		
		if(p!=null)
		{
			p.setTicketType("rac");
			racQueue.add(p);
		}
	}

	private static void addRacToBerth(Passenger p) {
		
		if(p!=null)
		{
			p.setPreference(preferenceTracker);
			p.setSeatNumber(canceledSeatNumber);
			p.setTicketType("berth");
			
			if(preferenceTracker == 'U') {
				upperList.add(p);
			}
			else if (preferenceTracker == 'M') {
				middleList.add(p);
			}
			else {
				lowerList.add(p);
			}
			
			confirmedList.add(p);
			seatNumberWithBerth.remove(canceledSeatNumber);
			preferenceTracker = '\0';
			canceledSeatNumber = 0;
		}
	}

	private static void deleteFromAllLists(Passenger p) {

		confirmedList.remove(p);
		upperList.remove(p);
		lowerList.remove(p);
		middleList.remove(p);
	}

	public static Map<Integer, Character> getSeatNumberWithBerth() {
		return seatNumberWithBerth;
	}

}
