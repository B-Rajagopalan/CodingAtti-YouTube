package sqrt;

public class sqrt3 
{
	public static void main(String[] args) 
	{
		int n=40;
		
		double x = n;
		double root = 0;
		
		while(true)
		{
			root = (x+(n/x))/2;
			
			double value = Math.abs(root-x);
			
			if(value<0.1)
				break;
			
			x=root;
		}
		
		System.out.println(root);
	}
}       //TC - O(log n f(n))
