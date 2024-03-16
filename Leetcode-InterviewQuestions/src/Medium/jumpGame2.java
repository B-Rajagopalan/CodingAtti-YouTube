package Medium;

public class jumpGame2 {
	public static int jump(int[] nums) {
        int max=0,count=0,tempMax=0;

        for(int i=0;i<nums.length-1;i++)
        {
            max = Math.max(max,i+nums[i]);

            if(max>=nums.length-1) {
                count++;                
                break;
            }
            
            if(tempMax==i) {
                count++;
                tempMax=max;
            }
        }
 
        return count;
    }
	
	public static void main(String[] args) {
		System.out.println(jump(new int[] {2,3,1,1,4}));
	}
}
