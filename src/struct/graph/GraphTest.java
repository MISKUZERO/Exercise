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
                    (int) (Math.random() * 9) + 1, true))
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
        int[][] rets = graph.singleShortestRoute(7);
        int[] costs = rets[0], paths = rets[1];
        for (int i = 0; i < paths.length; i++)
            System.out.print(i + " ");
        System.out.println("\n---------------------------");
        for (int path : paths)
            System.out.print(path + " ");
        System.out.print(" -- paths\n");
        for (int cost : costs) {
            if (cost == Integer.MAX_VALUE)
                System.out.print("* ");
            else
                System.out.print(cost + " ");
        }
        System.out.print(" -- costs\n\n");
        Graph<Integer, int[]> spanningTree = graph.minimumSpanningTree(7);
        System.out.println(spanningTree);
    }
}
