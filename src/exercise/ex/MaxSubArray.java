package exercise.ex;


public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(new MaxSubArray().maxSubArrayNew(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {
        int[] max = new int[]{nums[0]};
        max[0] = Math.max(dfs(nums.length - 1, nums, max), max[0]);
        return max[0];
    }

    public int dfs(int i, int[] nums, int[] max) {
        if (i == 0)
            return nums[0];
        int num = nums[i];
        int out = dfs(i - 1, nums, max);
        max[0] = Math.max(max[0], out);
        return Math.max(out + num, num);
    }

    public int maxSubArrayNew(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
