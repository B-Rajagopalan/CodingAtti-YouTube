package practice;

public class zigzag 
{
	public static void main(String[] args) 
	{
		String s = "PAYPALISHIRING";
		int numRows = 1;
		
		System.out.println(convert(s, numRows));
		
	}
	
	public static String convert(String s, int numRows) {
		
		if(numRows==1)
			return s;
		
		String result = "";
		
		for(int i=0;i<numRows;i++)
		{
			int j =i;
			int flag = 1;
			while(j<s.length())
			{
				result+=s.charAt(j);
				
				if(i==0 || i==numRows-1)
				{
					j+=(numRows-1)*2;
				}
				else if(flag%2!=0)
				{
					j+=(numRows-1)*2-(2*i);
				}
				else
				{
					j+=(2*i);
				}
				flag++;
			}
		}
		
		return result;
			
	}
}
	
