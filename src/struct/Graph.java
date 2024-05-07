package struct;

import java.util.List;
import java.util.function.Consumer;

public interface Graph<E> {

    boolean addNode(E node);

    boolean addEdge(E node1, E node2);

    E firstNode();

    E nextNode(E cur);

    List<E> getAll();

    void bfs(Consumer<E> consumer);

    void dfs(Consumer<E> consumer);

}
