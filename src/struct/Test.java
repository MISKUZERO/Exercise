package struct;


import exercise.GraphUtil;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        int V = 9, E = 9;
        ArrayList<int[]> edges = GraphUtil.getEdges(V, E);
        StaticLinkedGraphAdapter graph = new StaticLinkedGraphAdapter(V);
        for (int[] edge : edges)
            graph.addEdge(new StaticLinkedGraph.Node(edge[0]), new StaticLinkedGraph.Node(edge[1]));
        System.out.println(graph);

        graph.bfs(5, node -> System.out.print(node.id + " "));
    }
}
