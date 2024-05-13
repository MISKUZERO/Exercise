package exercise;

import java.util.ArrayList;

public class DFS {
    public static void main(String[] args) {
//        int[] max = {2, 3, 5};
        int max = 5;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
//        cartesianProduct(0, max, new ArrayList<>(), res);
        permutation(0, max, new boolean[max], new ArrayList<>(), res);
//        combination(2, max, new ArrayList<>(), res);
        for (ArrayList<Integer> re : res)
            System.out.println(re);
        System.out.println(res.size());
    }

    /**
     * 求 layer（包括）到 max（不包括）之间的笛卡尔积。
     *
     * <p>例如：一个元素串 S := {a, b, c}；
     * 假设各元素 a, b, c 分别有 2, 3, 5 种状态。
     * 则其广义的笛卡尔积为：a × b × c；
     * 一种有 2 * 3 * 5 = 30 种状态。
     *
     * @param layer 开始索引（层数）开始索引（层数）<b>注意：正常使用请填 0 ！</b>
     * @param max   结束索引（层数）
     * @param nums  临时保存的结果集
     */
    public static void cartesianProduct(int layer, int[] max,
                                        ArrayList<Integer> nums, ArrayList<ArrayList<Integer>> res) {
        if (layer == max.length) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for (int i = 0; i < max[layer]; i++) {
            nums.add(i);
            cartesianProduct(layer + 1, max, nums, res);
            nums.remove(nums.size() - 1);
        }
    }

    /**
     * 求 layer（包括）到 max（不包括）之间的全排列数。
     *
     * @param layer  开始索引（层数）<b>注意：正常使用请填 0 ！</b>
     * @param max    结束索引（层数）
     * @param nums   临时保存的结果集
     * @param visits 临时记录各元素访问情况
     * @param res    收集的结果集
     */
    public static void permutation(int layer, int max, boolean[] visits,
                                   ArrayList<Integer> nums, ArrayList<ArrayList<Integer>> res) {
        if (layer == max) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for (int i = 0; i < max; i++)
            if (!visits[i]) {
                visits[i] = true;
                nums.add(i);
                permutation(layer + 1, max, visits, nums, res);
                nums.remove(nums.size() - 1);
                visits[i] = false;
            }
    }

    /**
     * 求 layer（包括）到 max（不包括）之间的组合数。
     *
     * @param layer 开始索引（层数）
     * @param max   结束索引（层数）
     * @param nums  临时保存的结果集
     * @param res   收集的结果集
     */
    public static void combination(int layer, int max,
                                   ArrayList<Integer> nums, ArrayList<ArrayList<Integer>> res) {
        if (layer == max)
            return;
        for (int i = layer; i < max; i++) {
            nums.add(i);
            res.add(new ArrayList<>(nums));
            combination(i + 1, max, nums, res);
            nums.remove(nums.size() - 1);
        }
    }
}
