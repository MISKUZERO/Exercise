package struct;


import exercise.GraphUtil;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        int V = 15, E = 45;
        ArrayList<int[]> edges = GraphUtil.getEdges(V, E);
        IStaticLinkedGraph graph = new IStaticLinkedGraph(V);
        for (int[] edge : edges)
            graph.addEdge(new StaticLinkedGraph.Node(edge[0]), new StaticLinkedGraph.Node(edge[1]));
        System.out.println(graph);

        graph.breadthFirstSearch(5, (node, distance) -> System.out.print(node.id + "(" + distance + ") "));
        System.out.println("\n========================================================================================================================================");
        graph.depthFirstSearch(5, node -> System.out.print(node.id + " "));
    }
}
