package exercise;

import java.util.*;

public class Question4E {

    public static void main(String[] args) {
        int v = GraphUtil.v;
        ArrayList<int[]> edges = GraphUtil.edges;

        long t = System.currentTimeMillis();
        int count = 0;
        final int MAX = 1 << v;
        for (int i = 0; i < MAX; i++) {
            boolean test = true;
            for (int[] edge : edges) {
                if (((i >> edge[0] - 1) & 1) == 1 &&
                        ((i >> edge[1] - 1) & 1) == 1) {
                    test = false;
                    break;
                }
            }
            if (test)
                count++;
        }
        System.out.println("（E）红色节点不相邻的情况总数：" + count);
        System.out.println("（E）用时：" + (System.currentTimeMillis() - t) + "ms");
    }
}