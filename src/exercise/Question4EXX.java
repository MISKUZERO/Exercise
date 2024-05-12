package exercise;

import struct.graph.StaticLinkedGraph;

import java.util.*;

public class Question4EXX {


    public static void main(String[] args) {
        int v = GraphUtil.v;
        ArrayList<int[]> edges = GraphUtil.edges;

        StaticLinkedGraph graph = new StaticLinkedGraph(v);
        for (int[] edge : edges)
            graph.addEdge(edge[0], edge[1], 1, false);

        long t = System.currentTimeMillis();
        int count = 0;
        final int MAX_COUNT = 1 << v;
        boolean[] dp = new boolean[MAX_COUNT];
        for (int i = 0; i < MAX_COUNT; i++)
            if (!dp[i]) {
                int[] paints = new int[v];
                int pSize = 0;
                for (int j = 0; j < v; j++)
                    if ((i & (1 << j)) != 0)
                        paints[pSize++] = j + 1;
                graph.paint(paints, pSize, true);
                if (graph.test())
                    count++;
                else {
                    int[] bits = new int[v], _bits = new int[v];
                    ArrayList<Integer> bitList = new ArrayList<>();
                    for (int k = 0; k < v; k++)
                        if ((i & (1 << k)) != 0) {
                            bits[k] = 1;
                            bitList.add(k);
                        }
                    System.arraycopy(bits, 0, _bits, 0, v);
                    do {
                        for (int k = 0; k < v; k++)
                            if (!bitList.contains(k)) {
                                _bits[k]++;
                                break;
                            }
                        for (int k = 0; k < v; k++) {
                            if (_bits[k] == 2) {
                                _bits[k] = 0;
                                for (int l = k + 1; l < v; l++)
                                    if (!bitList.contains(l)) {
                                        _bits[l]++;
                                        break;
                                    }
                            }
                        }
                        int num = 0, dec = 1;
                        for (int k = 0; k < v; k++) {
                            if (_bits[k] == 1)
                                num += dec;
                            dec <<= 1;
                        }
                        dp[num] = true;
                    } while (!Arrays.equals(_bits, bits));
                }
                graph.paint(paints, pSize, false);
            }
        System.out.println("（EXX）红色节点不相邻的情况总数：" + count);
        System.out.println("（EXX）用时：" + (System.currentTimeMillis() - t) + "ms");
    }
}

