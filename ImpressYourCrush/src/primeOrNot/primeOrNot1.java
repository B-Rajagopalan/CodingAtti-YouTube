package primeOrNot;

public class primeOrNot1 
{
	public static void main(String[] args) 
	{
		int n = 1, count=0;
		
		for(int i=1;i<=n;i++)
		{
			if(n%i==0)
			{
				count++;
			}
		}
		
		if(count==2 || n==1)
			System.out.println("Prime");
		else
			System.out.println("Not a prime");
	}
}
 // O(n)
