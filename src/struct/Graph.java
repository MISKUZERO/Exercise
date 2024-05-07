package struct;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Graph<V, E> {

    boolean addNode(V v);

    boolean addEdge(V v1, V v2);

    V firstVertex(V root);

    V nextVertex(V root, V cur);

    List<V> getVertexes();

    List<E> getEdges(V v);

    void bfs(V root, BiConsumer<V, Integer> consumer);

    void dfs(V root, Consumer<V> consumer);

}
