package exercise.ex;

public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += nums[j];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

}
