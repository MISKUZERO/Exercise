package exercise;

import struct.graph.StaticLinkedGraph;

import java.util.*;

public class Question4EX {


    public static void main(String[] args) {
        int v = GraphUtil.v;
        ArrayList<int[]> edges = GraphUtil.edges;

        StaticLinkedGraph graph = new StaticLinkedGraph(v);
        for (int[] edge : edges)
            graph.addEdge(edge[0], edge[1], 1, false);

        long t = System.currentTimeMillis();
        int count = 0;
        final int MAX_COUNT = 1 << v;
        ArrayList<Integer> masks = new ArrayList<>();
        for (int i = 0; i < MAX_COUNT; i++) {
            boolean ex = true;
            for (int mask : masks)
                if ((i & mask) == mask) {
                    ex = false;
                    break;
                }
            if (ex) {
                int[] paints = new int[v];
                int pSize = 0;
                for (int j = 0; j < v; j++)
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

