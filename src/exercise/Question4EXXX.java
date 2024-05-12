package exercise;

import java.util.*;

public class Question4EXXX {

    public static void main(String[] args) {
        int v = GraphUtil.v;
        ArrayList<int[]> edges = GraphUtil.edges;

        long t = System.currentTimeMillis();
        dfs(0, v, new int[v + 1], 1, edges);
        System.out.println("（EXXX）红色节点不相邻的情况总数：" + count);
        System.out.println("（EXXX）用时：" + (System.currentTimeMillis() - t) + "ms");
    }

    static int count;

    public static void dfs(int index, int end, int[] nums, int size, ArrayList<int[]> edges) {
        if (index == end) {
            //处理
            boolean test = true;
            for (int[] edge : edges) {
                if (nums[edge[0]] == 1 && nums[edge[1]] == 1) {
                    test = false;
                    break;
                }
            }
            if (test)
                count++;
            return;
        }
        for (int i = 0; i < 2; i++) {
            nums[size++] = i;
            dfs(index + 1, end, nums, size, edges);
            size--;
        }
    }
}