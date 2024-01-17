import java.util.Arrays;
import java.util.HashMap;

public class twoSum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(twoSumOptimized(nums, target)));
    }

    //brute force
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    //optimized
    public static int[] twoSumOptimized(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> check = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = target - nums[i];
            if (check.containsKey(sum)) {
                result[0] = i;
                result[1] = check.get(sum);
                return result;
            }
            check.put(nums[i], i);
        }
        return result;
    }
}
