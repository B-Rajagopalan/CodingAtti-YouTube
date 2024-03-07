package Medium;

public class buyStock2 
{
	public static void main(String[] args) {
		System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
	}
	
	public static int maxProfit(int[] arr) {
        int maxProfit=0,min=arr[0];

        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]<min)
            {
                min = arr[i];
            }
            else
            {
                maxProfit+=arr[i]-min;
                min = arr[i];
            }
        }

        return maxProfit;
    }
}
