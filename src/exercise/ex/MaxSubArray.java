package exercise.ex;


public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaxSubArray().maxSubArray(nums));
        System.out.println(new MaxSubArray().maxSubArrayNew(nums));
        System.out.println(new MaxSubArray().maxSubArray(nums, 0, nums.length - 1));
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

    private int maxSubArray(int[] nums, int lo, int hi) {
        if (hi == lo)
            return nums[lo];
        int mid = lo + (hi - lo) / 2;// 计算左半部分数组的最大子数组和
        int max_left = maxSubArray(nums, lo, mid);// 计算右半部分数组的最大子数组和
        int max_right = maxSubArray(nums, mid + 1, hi);
        int max_mid = maxMidSubArray(nums, lo, mid, hi);
//        int max_mid = max_left + max_right;
        return Math.max(max_left, Math.max(max_mid, max_right));
    }

    private int maxMidSubArray(int[] nums, int lo, int mid, int hi) {
        // 计算中间线左侧（且紧挨着中间线）的最大子数组和
        int max_mid_left = 0;
        if (mid >= lo) {
            max_mid_left = nums[mid];
            int sum = 0;
            for (int i = mid; i >= lo; i--) {
                sum += nums[i];
                max_mid_left = Math.max(max_mid_left, sum);
            }
        }

        // 计算中间线右侧（且紧挨着中间线）的最大子数组和
        int max_mid_right = 0;
        if (mid + 1 <= hi) {
            max_mid_right = nums[mid + 1];
            int sum = 0;
            for (int i = mid + 1; i <= hi; i++) {
                sum += nums[i];
                max_mid_right = Math.max(max_mid_right, sum);
            }
        }

        return max_mid_left + max_mid_right;
    }


}
