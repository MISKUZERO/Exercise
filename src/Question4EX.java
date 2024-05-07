import struct.Graph;

import java.util.*;

public class Question4EX {


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String fLine = scanner.nextLine();
//        String[] s = fLine.split(" ");
//        final int V = Integer.parseInt(s[0]);
//        final int E = Integer.parseInt(s[1]);
//        Graph graph = new Graph(V);
//        for (int i = 0; i < E; i++) {
//            String line = scanner.nextLine();
//            String[] ss = line.split(" ");
//            graph.addEdge(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]));
//        }
        int V = Test4.v;
        ArrayList<int[]> edges = Test4.edges;

        Graph graph = new Graph(V);
        for (int[] edge : edges)
            graph.addEdge(edge[0], edge[1]);

        long t = System.currentTimeMillis();
        int count = 0;
        final int MAX_COUNT = 1 << V;
        ArrayList<Integer> masks = new ArrayList<>();
        for (int i = 0; i < MAX_COUNT; i++) {
            boolean ex = true;
            for (int mask : masks)
                if ((i & mask) == mask) {
                    ex = false;
                    break;
                }
            if (ex) {
                int[] paints = new int[V];
                int pSize = 0;
                for (int j = 0; j < V; j++)
                    if ((i & (1 << j)) != 0)
                        paints[pSize++] = j + 1;
                graph.paint(paints, pSize, true);
                if (graph.test())
                    count++;
                else
                    masks.add(i);
                graph.paint(paints, pSize, false);
            }
        }
        System.out.println("（EX）红色节点不相邻的情况总数：" + count);
        System.out.println("（EX）用时：" + (System.currentTimeMillis() - t) + "ms");
    }
}

