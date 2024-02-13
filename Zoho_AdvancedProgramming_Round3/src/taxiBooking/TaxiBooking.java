package taxiBooking;

import java.util.ArrayList;

public class TaxiBooking 
{
	private static ArrayList<Taxi> taxiList = new ArrayList<Taxi>();
	private static int taxiListLimit = 4,idGenerator = 1;
	private static ArrayList<Taxi> taxiBookedHistory = new ArrayList<Taxi>();
	
	public static String booking(char pickupLocation, char dropLocation, int pickupTime) throws CloneNotSupportedException
	{
		if(taxiList.size()<taxiListLimit)
		{
			taxiList.add(new Taxi());
		}
		
		int min = Integer.MAX_VALUE;
		Taxi taxiReady = null;
		
		for(Taxi t : taxiList)
		{
			if(t.getDropTime()<=pickupTime && Math.abs(pickupLocation - t.getCurrentLocation()) < min)
			{
				taxiReady = t;
				min = Math.abs(pickupLocation - t.getCurrentLocation());
			}
		}
		
		if(taxiReady!=null)
		{
			taxiReady.setCustomerId(idGenerator++);
			taxiReady.setPickupTime(pickupTime);
			taxiReady.setPickupLocation(pickupLocation);
			taxiReady.setDropLocation(dropLocation);
			taxiReady.setCurrentLocation(dropLocation);
			taxiReady.setDropTime(pickupTime + Math.abs(dropLocation-pickupLocation));
			taxiReady.setEarnings((taxiReady.getEarnings()) + Math.abs(dropLocation-pickupLocation) * (100+(10*10)));
			taxiReady.setTaxiId(taxiList.indexOf(taxiReady)+1);
			taxiBookedHistory.add((Taxi)taxiReady.clone()); //clone object
		}
		
		return taxiReady!=null?"Taxi number "+taxiReady.getTaxiId()+" is booked!":"Taxis not available";
	}

	public static void display() {
		
		System.out.println("-----------------");
		for(Taxi t : taxiBookedHistory)
		{
			System.out.println(t.toString());
			System.out.println("-----------------");
		}
	}
}
