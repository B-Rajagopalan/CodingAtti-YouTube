package Easy;

public class buyStock 
{
	public static void main(String[] args) 
	{
		int[] prices = {7,1,5,3,6,4};
		System.out.println(maxProfit(prices));
	}
	
	public static int maxProfit(int[] prices) {
        int max=0,min=prices[0];

        for(int i=0;i<prices.length;i++)
        {
            if(prices[i]<min)
            {
                min = prices[i];
            }
            else
            {
                max = Math.max(max,prices[i]-min);
            }
        }

        return max;
    }
}
