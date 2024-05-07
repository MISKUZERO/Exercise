package struct;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ArrayGraph implements Graph<Integer, Integer> {

    private int[][] vertexes;

    @Override
    public boolean addNode(Integer integer) {
        return false;
    }

    @Override
    public boolean addEdge(Integer v1, Integer v2) {
        return false;
    }

    @Override
    public Integer firstVertex(Integer root) {
        return null;
    }

    @Override
    public Integer nextVertex(Integer root, Integer cur) {
        return null;
    }

    @Override
    public List<Integer> getVertexes() {
        return null;
    }

    @Override
    public List<Integer> getEdges(Integer integer) {
        return null;
    }

    @Override
    public void breadthFirstSearch(Integer root, BiConsumer<Integer, Integer> consumer) {

    }

    @Override
    public void depthFirstSearch(Integer root, Consumer<Integer> consumer) {

    }
}
