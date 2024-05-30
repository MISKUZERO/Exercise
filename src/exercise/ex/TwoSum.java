package exercise.ex;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum().twoSum(new int[]{3, -2, -4, 2, -4, 5, 7, 8}, 6)));
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> idxHashMap = new HashMap<>();
        for (int i = 0, len = nums.length; i < len; i++) {
            int num = nums[i];
            Integer idx;
            if ((idx = idxHashMap.get(target - num)) == null)
                idxHashMap.put(num, i);
            else
                return new int[]{idx, i};
        }
        return null;
    }
}
