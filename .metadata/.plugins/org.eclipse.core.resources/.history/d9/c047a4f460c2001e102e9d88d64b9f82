package Easy;

import java.util.ArrayList;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        System.out.println(removeElementTwoPointer(nums, val));
    }

    public static int removeElement(int[] nums, int val) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int c : nums) {
            if (c != val) result.add(c);
        }
        for (int i = 0; i < result.size(); i++) {
            nums[i] = result.get(i);
        }
        return result.size();
    }

    public static int removeElementTwoPointer(int[] nums, int val) {
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] != val) nums[i++] = nums[j++];
            else j++;
        }
        return i;
    }
}
