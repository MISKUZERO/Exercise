package struct;


import exercise.GraphUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int v = 9, e = 9;
        ArrayList<int[]> edges = GraphUtil.getEdges(v, e);

        Graph<Integer, int[]> graph = new StaticArrayGraph(v);
        IStaticLinkedGraph linkedGraph = new IStaticLinkedGraph(v);

        for (int[] edge : edges)
            if (!graph.addEdge(edge[0], edge[1]))
                throw new UnsupportedOperationException();

        for (int[] edge : edges)
            if (!linkedGraph.addEdge(new StaticLinkedGraph.Node(edge[0]), new StaticLinkedGraph.Node(edge[1])))
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
    }
}
