package Medium;

public class MaxSubArray {
    public static void main(String[] args) {
        int[] ques = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArrayBruteForce(ques));
        System.out.println(maxSubArrayOptimized(ques));
    }

    public static int maxSubArrayBruteForce(int[] nums) {
        int sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j++) {
                temp += nums[j];
                sum = Math.max(sum, temp);
            }
        }
        return sum;
    }

    public static int maxSubArrayOptimized(int[] nums) {
        int temp = 0, sum = 0;
        for (int n : nums) {
            temp += n;
            sum = Math.max(sum, temp);
            if (temp < 0) temp = 0;
        }
        return sum;
    }
}
