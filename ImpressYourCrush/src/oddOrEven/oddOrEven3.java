package oddOrEven;

public class oddOrEven3 
{
	public static void main(String[] args) 
	{
		int n = 3;
		
		if(isEven(n))
			System.out.println("Even");
		else
			System.out.println("Odd");
			
	}

	private static boolean isEven(int n) {
		
		return (n&1)==0;
	}
}