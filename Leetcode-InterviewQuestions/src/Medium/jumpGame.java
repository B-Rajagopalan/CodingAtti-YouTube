package Medium;

public class jumpGame {
	public static boolean canJump(int[] nums) {
        int max=0, i=0;
        boolean result = false;
        
        while(i<nums.length)
        {
            max = Math.max(max,i+nums[i]);

            if(max>=nums.length-1) {
            	result=true;
            	break;
            }

            if(i==max) {
                result = false;
                break;
            }
            
            i++;
        }

        return result; //best practice is to use boolean variable
    }
	
	public static void main(String[] args) {
		System.out.println(canJump(new int[] {3,2,1,0,4}));
	}
}
