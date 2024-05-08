package struct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class StaticArrayGraph implements Graph<Integer, int[]> {

    private final int[][] vertexes;
    private final ArrayList<int[]> edges;

    public StaticArrayGraph(int vertexCount) {
        int size = vertexCount + 1;
        vertexes = new int[size][size];
        edges = new ArrayList<>();
    }

    @Override
    public boolean addNode(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addEdge(Integer v1, Integer v2) {
        return addEdge(v1, v2, 1, false);
    }

    public boolean addEdge(Integer v1, Integer v2, int cost) {
        return addEdge(v1, v2, cost, false);
    }

    public boolean addEdge(Integer v1, Integer v2, boolean directed) {
        return addEdge(v1, v2, 1, directed);
    }

    public boolean addEdge(Integer v1, Integer v2, int cost, boolean directed) {
        int[][] vertexes = this.vertexes;
        int size = vertexes.length;
        if (v1 >= size || v2 >= size) return false;
        if (directed)
            vertexes[v1][v2] = cost;
        else {
            vertexes[v1][v2] = cost;
            vertexes[v2][v1] = cost;
        }
        edges.add(new int[]{v1, v2, cost});
        return true;
    }

    @Override
    public Integer firstVertex(Integer root) {
        int[] vs = vertexes[root];
        int length = vs.length;
        for (int i = 1; i < length; i++)
            if (vs[i] != 0)
                return i;
        return -1;
    }

    @Override
    public Integer nextVertex(Integer root, Integer cur) {
        int[] vs = vertexes[root];
        int length = vs.length;
        for (int i = cur + 1; i < length; i++)
            if (vs[i] != 0)
                return i;
        return -1;
    }

    @Override
    public List<int[]> getEdges(Integer v) {
        ArrayList<int[]> list = new ArrayList<>();
        ArrayList<int[]> edges = this.edges;
        for (int[] edge : edges)
            if (edge[0] == v || edge[1] == v)
                list.add(edge);
        return list;
    }

    @Override
    public void breadthFirstSearch(Integer root, BiConsumer<Integer, Integer> biConsumer) {
        int[][] vertexes = this.vertexes;
        boolean[] visits = new boolean[vertexes.length];
        int idx = root, length = visits.length;
        boolean notFull = true;
        while (notFull) {
            doBFS(idx, biConsumer, visits);
            notFull = false;
            for (idx = 1; idx < length; idx++)
                if (!visits[idx]) {
                    notFull = true;
                    break;
                }
        }
    }

    private void doBFS(int root, BiConsumer<Integer, Integer> biConsumer, boolean[] visits) {
        LinkedList<Integer> queue = new LinkedList<>();
        final int LAYER_FLAG = Integer.MAX_VALUE;
        int distance = 0;
        visits[root] = true;
        biConsumer.accept(root, distance++);
        queue.addLast(root);
        queue.addLast(LAYER_FLAG);
        while (!queue.isEmpty()) {
            Integer poll = queue.pollFirst();
            if (poll == LAYER_FLAG) {
                distance++;
                if (queue.isEmpty())
                    return;
                else
                    poll = queue.pollFirst();
                queue.addLast(LAYER_FLAG);
            }
            for (int cur = firstVertex(poll);
                 cur != -1;
                 cur = nextVertex(poll, cur)) {
                if (!visits[cur]) {
                    visits[cur] = true;
                    biConsumer.accept(cur, distance);
                    queue.addLast(cur);
                }
            }
        }
    }

    @Override
    public void depthFirstSearch(Integer root, Consumer<Integer> consumer) {
        int[][] vertexes = this.vertexes;
        boolean[] visits = new boolean[vertexes.length];
        int idx = root, length = visits.length;
        boolean notFull = true;
        while (notFull) {
            doDFS(idx, consumer, visits);
            notFull = false;
            for (idx = 1; idx < length; idx++)
                if (!visits[idx]) {
                    notFull = true;
                    break;
                }
        }
    }

    private void doDFS(Integer root, Consumer<Integer> consumer, boolean[] visits) {
        visits[root] = true;
        consumer.accept(root);
        for (Integer cur = firstVertex(root);
             cur != -1;
             cur = nextVertex(root, cur)) {
            if (!visits[cur])
                doDFS(cur, consumer, visits);//递归
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        int[][] vertexes = this.vertexes;
        int size = vertexes.length;
        for (int i = 0; i < size; i++)
            ret.append(" ").append(i).append(" ");
        ret.append("\n");
        for (int i = 1; i < size; i++) {
            int[] vs = vertexes[i];
            for (int j = 0; j < size; j++)
                if (j == 0)
                    ret.append(" ").append(i).append(" ");
                else if (vs[j] == 0)
                    ret.append(" · ");
                else
                    ret.append(" ").append(vs[j]).append(" ");
            ret.append("\n");
        }
        return ret.toString();
    }
}
