package exercise;

import java.util.ArrayList;

public class AlgoTest {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int max = 5;
        dfs(0, max, new ArrayList<>(), new boolean[max],res);
        System.out.println(res);
        System.out.println(res.size());
    }

    public static void dfs(int layer, int[] max, ArrayList<Integer> nums) {
        if (layer == max.length) {
            System.out.println(nums);
            return;
        }
        for (int i = 0; i < max[layer]; i++) {
            nums.add(i);
            dfs(layer + 1, max, nums);
            nums.remove(nums.size() - 1);
        }
    }

    public static void dfs(int layer, int max, ArrayList<Integer> nums, boolean[] visits,
                           ArrayList<ArrayList<Integer>> res) {
        if (layer == max) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for (int i = 0; i < max; i++) {
            if (!visits[i]) {
                visits[i] = true;
                nums.add(i);
                dfs(layer + 1, max, nums, visits, res);
                nums.remove(nums.size() - 1);
                visits[i] = false;
            }
        }
    }
}
