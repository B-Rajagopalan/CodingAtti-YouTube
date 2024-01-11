package oddOrEven;

public class OddOrEven2 
{
	public static void main(String[] args) 
	{
		int n = 21;
		
		int div = n/2;
		int mul = div*2;
		
		if(n==mul)
			System.out.println("Even");
		else
			System.out.println("Odd");
	}
}