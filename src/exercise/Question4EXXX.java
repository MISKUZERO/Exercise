package exercise;

import java.util.*;

public class Question4EXXX {

    public static void main(String[] args) {
        int v = GraphUtil.v;
        ArrayList<int[]> edges = GraphUtil.edges;

        long t = System.currentTimeMillis();
        dfs(new int[v + 1], 1, v + 1, edges);
        System.out.println("（EXXX）红色节点不相邻的情况总数：" + count);
        System.out.println("（EXXX）用时：" + (System.currentTimeMillis() - t) + "ms");
    }

    static int count;

    public static void dfs(int[] nums, int index, int end, ArrayList<int[]> edges) {
        if (index == end) {
            //处理
            boolean test = true;
            for (int[] edge : edges)
                if (nums[edge[0]] == 1 && nums[edge[1]] == 1) {
                    test = false;
                    break;
                }
            if (test)
                count++;
            return;
        }
        branch:
        for (int i = 1; i > -1; i--) {
            nums[index++] = i;
            if (index > 2) //剪枝
                for (int[] edge : edges) {
                    int e0 = edge[0], e1 = edge[1];
                    if ((e0 < index && e1 < index) && (nums[e0] == 1 && nums[e1] == 1)) {
                        index--;
                        continue branch;
                    }
                }
            dfs(nums, index--, end, edges);
        }
    }
}