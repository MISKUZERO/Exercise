package exercise;

import java.util.*;

public class Question4E {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String fLine = scanner.nextLine();
//        String[] s = fLine.split(" ");
//        final int v = Integer.parseInt(s[0]);
//        final int e = Integer.parseInt(s[1]);
//        int[][] edges = new int[e][];
//        for (int i = 0; i < e; i++) {
//            String line = scanner.nextLine();
//            String[] ss = line.split(" ");
//            edges[i] = new int[]{Integer.parseInt(ss[0]), Integer.parseInt(ss[1])};
//        }

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