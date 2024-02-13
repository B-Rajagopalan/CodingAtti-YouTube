package railwayRerservation;

import java.util.*;
import java.util.Map.Entry;

public class TicketBooking 
{	
	private static int berthLimit = 6/3;
	private static int racLimit = 1;
	private static int waitingListLimit = 1;
	
	private static int upperSeatNumber = 1;
	private static int middleSeatNumber = 2;
	private static int lowerSeatNumber = 3;
	
	static ArrayList<Passenger> confirmedList = new ArrayList<Passenger>();
	
	static ArrayList<Passenger> upperList = new ArrayList<Passenger>();
	static ArrayList<Passenger> middleList = new ArrayList<Passenger>();
	static ArrayList<Passenger> lowerList = new ArrayList<Passenger>();
	
	
	static Queue<Passenger> racQueue = new LinkedList<Passenger>();
	static Queue<Passenger> waitingQueue = new LinkedList<Passenger>();
	
	public static void bookTicket(Passenger p)
	{
		if(upperList.size() == berthLimit && lowerList.size() == berthLimit && middleList.size() == berthLimit)
		{
			if(updateRacQueue(p))
			{
				System.out.println("Added to RAC\nYour ticket id is "+p.getId());
			}
			else if(updateWaitingQueue(p))
			{
				System.out.println("Added to Waiting List\nYour ticket id is "+p.getId());			
			}
			else
			{
				p.setId(p.getId()-1); 
				System.out.println("Tickets not available");
			}
		}
		else if(checkAvailability(p))
		{
			System.out.println("Booking confirmed\nYour ticket id is "+p.getId());
			p.setTicketType("berth");
			confirmedList.add(p);
		}
		else
		{
			System.out.println(p.getPreference()+" is not available");
			p.setId(p.getId()-1);
			availableList();
		}
	}

	private static boolean updateWaitingQueue(Passenger p) {
		
		if(waitingQueue.size()<waitingListLimit) {
			p.setTicketType("waitingList");
			waitingQueue.add(p);
			return true;
		}
		
		return false;
	}

	private static boolean updateRacQueue(Passenger p) {
		
		if(racQueue.size()<racLimit) {
			p.setTicketType("rac");
			racQueue.add(p);
			return true;
		}
		
		return false;
	}

	private static void availableList() {
		System.out.println("Check out the no of seats available");
		System.out.println("Upper Berth "+(berthLimit - upperList.size()));
		System.out.println("Middle Berth "+(berthLimit - middleList.size()));
		System.out.println("Lower Berth "+(berthLimit - lowerList.size()));
	}

	private static boolean checkAvailability(Passenger p) 
	{
		Map<Integer,Character> map = TicketCanceling.getSeatNumberWithBerth();
		
		if(p.getPreference()=='U') {
			if(upperList.size()<berthLimit)
			{
				if(!map.isEmpty())
				{
					getSeatDetails(map, p);
				}
				else
				{
					p.setSeatNumber(upperSeatNumber);
					upperSeatNumber+=3; 
				}
			
				upperList.add(p);
				return true;
			}
		}
		else if (p.getPreference()=='M') {
			if(middleList.size()<berthLimit)
			{
				if(!map.isEmpty())
				{
					getSeatDetails(map, p);
				}
				else
				{
					p.setSeatNumber(middleSeatNumber);
					middleSeatNumber+=3;
				}
			
				middleList.add(p);
				return true;
			}
		}
		else {
			if(lowerList.size()<berthLimit)
			{
				if(!map.isEmpty())
				{
					getSeatDetails(map, p);
				}
				else
				{
					p.setSeatNumber(lowerSeatNumber);
					lowerSeatNumber+=3;
				}
		
				lowerList.add(p);
				return true;
			}
		}
		
		return false;
	}

	public static void getSeatDetails(Map<Integer,Character> map,Passenger p)
	{
		int seatNumber = checkForPreferenceAvailability(map,p.getPreference());
		p.setSeatNumber(seatNumber);
		map.remove(seatNumber);
	}
	
	public static int checkForPreferenceAvailability(Map<Integer,Character> map,char preference)
	{
		int seatNumber = 0;
		
		for(Entry<Integer,Character> entry : map.entrySet())
		{
			if(preference == (char)entry.getValue())
			{
				seatNumber = (int)entry.getKey();
				break;
			}
		}
		return seatNumber;
	}
	
	public static void displayConfirmed()
	{
		System.out.println("-------------------------");
		for(Passenger p : confirmedList)
		{
			System.out.println(p.toString());
			System.out.println("-------------------------");
		}
	}
	
	public static void displayRAC()
	{
		System.out.println("-------------------------");
		for(Passenger p : racQueue)
		{
			System.out.println(p.toString());
			System.out.println("-------------------------");
		}
	}
	public static void displayWaiting()
	{
		System.out.println("-------------------------");
		for(Passenger p : waitingQueue)
		{
			System.out.println(p.toString());
			System.out.println("-------------------------");
		}
	}
}
