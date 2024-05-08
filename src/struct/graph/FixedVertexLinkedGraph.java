package struct.graph;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FixedVertexLinkedGraph implements Graph<StaticLinkedGraph.Node, int[]> {

    private final StaticLinkedGraph graph;

    public FixedVertexLinkedGraph(int vertexCount) {
        graph = new StaticLinkedGraph(vertexCount);
    }

    public FixedVertexLinkedGraph(StaticLinkedGraph graph) {
        this.graph = graph;
    }

    @Override
    public boolean addNode(StaticLinkedGraph.Node v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addEdge(StaticLinkedGraph.Node v1, StaticLinkedGraph.Node v2) {
        return graph.addEdge(v1.id, v2.id, 1, false);
    }

    @Override
    public StaticLinkedGraph.Node firstVertex(StaticLinkedGraph.Node v) {
        return graph.vertexes.get(v.id).next;
    }

    @Override
    public StaticLinkedGraph.Node nextVertex(StaticLinkedGraph.Node root, StaticLinkedGraph.Node cur) {
        return cur.next;
    }

    public List<int[]> getEdges(int vid) {
        return getEdges(graph.vertexes.get(vid));
    }

    @Override
    public List<int[]> getEdges(StaticLinkedGraph.Node v) {
        ArrayList<int[]> list = new ArrayList<>();
        ArrayList<int[]> edges = graph.edges;
        int vId = v.id;
        for (int[] edge : edges)
            if (edge[0] == vId || edge[1] == vId)
                list.add(edge);
        return list;
    }

    public void breadthFirstSearch(int vid, BiConsumer<StaticLinkedGraph.Node, Integer> biConsumer) {
        breadthFirstSearch(graph.vertexes.get(vid), biConsumer);
    }

    @Override
    public void breadthFirstSearch(StaticLinkedGraph.Node root, BiConsumer<StaticLinkedGraph.Node, Integer> biConsumer) {
        ArrayList<StaticLinkedGraph.Node> vertexes = graph.vertexes;
        boolean[] visits = new boolean[vertexes.size()];
        int idx = root.id, length = visits.length;
        boolean notFull = true;
        while (notFull) {
            doBFS(vertexes.get(idx), biConsumer, visits);
            notFull = false;
            for (idx = 1; idx < length; idx++)
                if (!visits[idx]) {
                    notFull = true;
                    break;
                }
        }
    }

    private void doBFS(StaticLinkedGraph.Node root, BiConsumer<StaticLinkedGraph.Node, Integer> biConsumer, boolean[] visits) {
        LinkedList<StaticLinkedGraph.Node> queue = new LinkedList<>();
        final StaticLinkedGraph.Node LAYER_FLAG = new StaticLinkedGraph.Node(0);
        int distance = 0;
        visits[root.id] = true;
        biConsumer.accept(root, distance++);
        queue.addLast(root);
        queue.addLast(LAYER_FLAG);
        while (!queue.isEmpty()) {
            StaticLinkedGraph.Node poll = queue.pollFirst();
            if (poll == LAYER_FLAG) {
                distance++;
                poll = queue.pollFirst();
                if (poll == null)
                    return;
                queue.addLast(LAYER_FLAG);
            }
            for (StaticLinkedGraph.Node cur = firstVertex(poll);
                 cur != null;
                 cur = nextVertex(null, cur)) {
                int id = cur.id;
                if (!visits[id]) {
                    visits[id] = true;
                    biConsumer.accept(cur, distance);
                    queue.addLast(cur);
                }
            }
        }
    }

    public void depthFirstSearch(int vid, Consumer<StaticLinkedGraph.Node> consumer) {
        depthFirstSearch(graph.vertexes.get(vid), consumer);
    }

    @Override
    public void depthFirstSearch(StaticLinkedGraph.Node root, Consumer<StaticLinkedGraph.Node> consumer) {
        ArrayList<StaticLinkedGraph.Node> vertexes = graph.vertexes;
        boolean[] visits = new boolean[vertexes.size()];
        int idx = root.id, length = visits.length;
        boolean notFull = true;
        while (notFull) {
            doDFS(vertexes.get(idx), consumer, visits);
            notFull = false;
            for (idx = 1; idx < length; idx++)
                if (!visits[idx]) {
                    notFull = true;
                    break;
                }
        }
    }

    @Override
    public int[][] singleShortestRoute(StaticLinkedGraph.Node root) {
        return null;
    }

    @Override
    public Graph<StaticLinkedGraph.Node, int[]> minimumSpanningTree(StaticLinkedGraph.Node root) {
        return null;
    }

    private void doDFS(StaticLinkedGraph.Node root, Consumer<StaticLinkedGraph.Node> consumer, boolean[] visits) {
        visits[root.id] = true;
        consumer.accept(root);
        for (StaticLinkedGraph.Node cur = firstVertex(root);
             cur != null;
             cur = nextVertex(null, cur)) {
            if (!visits[cur.id])
                doDFS(cur, consumer, visits);//递归
        }
    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ArrayList<StaticLinkedGraph.Node> vertexes = graph.vertexes;
        int size = vertexes.size();
        for (int i = 1; i < size; i++) {
            StaticLinkedGraph.Node cur = vertexes.get(i);
            StaticLinkedGraph.loadStringBuilder(ret, cur);
        }
        return ret.toString();
    }

}
