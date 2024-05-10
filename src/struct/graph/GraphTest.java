package struct.graph;


import exercise.GraphUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphTest {
    public static void main(String[] args) {
        int v = 7, e = 9;
        ArrayList<int[]> edges = GraphUtil.getEdges(v, e);

        FixedVertexMatrixGraph graph = new FixedVertexMatrixGraph(v);
        FixedVertexLinkedGraph linkedGraph = new FixedVertexLinkedGraph(v);

        for (int[] edge : edges)
            if (!graph.addEdge(edge[0], edge[1],
                    (int) (Math.random() * 5) + 1, true))
                throw new UnsupportedOperationException();

        for (int[] edge : edges)
            if (!linkedGraph.addEdge(new StaticLinkedGraph.Node(edge[0]),
                    new StaticLinkedGraph.Node(edge[1])))
                throw new UnsupportedOperationException();

        System.out.println(graph);
        System.out.println(linkedGraph);

        graph.breadthFirstSearch(5, (node, distance) -> System.out.print(node + ":" + distance + " "));
        System.out.println();
        graph.depthFirstSearch(5, node -> System.out.print(node + "   "));
        System.out.println();

        linkedGraph.breadthFirstSearch(5, (node, distance) -> System.out.print(node.id + ":" + distance + " "));
        System.out.println();
        linkedGraph.depthFirstSearch(5, node -> System.out.print(node.id + "   "));
        System.out.println();
        System.out.println();

        List<int[]> es = graph.getEdges(3);
        for (int[] edge : es)
            System.out.println(Arrays.toString(edge));
        System.out.println();

        List<int[]> eds = linkedGraph.getEdges(3);
        for (int[] edge : eds)
            System.out.println(Arrays.toString(edge));
        System.out.println();

        System.out.println(graph.minimumSpanningTree(7));

        System.out.println("== single ==");
        for (int i = 1; i <= 7; i++) {
            int[][] rets = graph.singleShortestRoute(i);
            for (int cost : rets[0]) {
                if (cost == Integer.MAX_VALUE)
                    System.out.print("* ");
                else
                    System.out.print(cost + " ");
            }
            System.out.println();
        }
        System.out.println(" -- costs");

        for (int i = 1; i <= 7; i++) {
            int[][] rets = graph.singleShortestRoute(i);
            for (int path : rets[1])
                if (path == Integer.MAX_VALUE)
                    System.out.print("* ");
                else
                    System.out.print(path + " ");
            System.out.println();
        }
        System.out.println(" -- paths");

        System.out.println("\n== all ==");
        int[][][] res = graph.allShortestRoute();
        for (int[] i : res[0]) {
            for (int j : i)
                if (j == Integer.MAX_VALUE)
                    System.out.print("* ");
                else
                    System.out.print(j + " ");
            System.out.println();
        }
        System.out.println(" -- costs");
        for (int[] i : res[1]) {
            for (int j : i)
                if (j == Integer.MAX_VALUE)
                    System.out.print("* ");
                else
                    System.out.print(j + " ");
            System.out.println();
        }
        System.out.println(" -- paths");
    }
}
