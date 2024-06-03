package exercise.ex;

import java.util.ArrayList;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] costs = {2, 5, 8, 9, 10, 17, 19, 21, 24, 28};
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int limitWeight = 17;
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        dfs(0, limitWeight, weights, new boolean[costs.length], costs.length, new ArrayList<>(), paths);
        System.out.println(paths.size());
        int max = 0;
        ArrayList<Integer> maxPath = new ArrayList<>();
        for (ArrayList<Integer> path : paths) {
            int sum = 0;
            for (Integer idx : path)
                sum += costs[idx];
            if (sum > max) {
                max = sum;
                maxPath = path;
            }
        }
        System.out.println(maxPath + ": " + max);
        System.out.println(maxVal(costs, weights, limitWeight));
    }


    public static int maxVal(int[] costs, int[] weights, int limitWeight) {
        return f(limitWeight, costs, weights, weights.length, new int[limitWeight + 1]);
    }

    public static int f(int n, int[] costs, int[] weights, int len, int[] dp) {
        if (dp[n] != 0) return dp[n];
        int max = 0;
        for (int i = 0; i < len; i++) {
            int weight = weights[i];
            if (n < weight) break;
            max = Math.max(max, costs[i] + f(n - weight, costs, weights, len, dp));
        }
        dp[n] = max;
        return max;
    }

    public static void dfs(int curWeight, int limitWeight, int[] weights, boolean[] visits, int len,
                           ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths) {
        if (curWeight > limitWeight) {
            ArrayList<Integer> list = new ArrayList<>(path);
            list.remove(list.size() - 1);
            paths.add(list);
            return;
        }
        if (curWeight == limitWeight) {
            paths.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
//            if (!visits[i]) {
//                visits[i] = true;
            path.add(i);
            dfs(curWeight + weights[i], limitWeight, weights, visits, len, path, paths);
            path.remove(path.size() - 1);
//                visits[i] = false;
//            }
        }
    }
}
