package struct.graph;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Graph<V, E> {

    boolean addNode(V v);

    boolean addEdge(V v1, V v2);

    V firstVertex(V root);

    V nextVertex(V root, V cur);

    List<E> getEdges(V v);

    void breadthFirstSearch(V root, BiConsumer<V, Integer> consumer);

    void depthFirstSearch(V root, Consumer<V> consumer);

    int[][] singleShortestRoute(V root);

    Graph<V, E> minimumSpanningTree(V root);

}
