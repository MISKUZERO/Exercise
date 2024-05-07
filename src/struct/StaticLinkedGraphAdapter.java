package struct;

import java.util.*;
import java.util.function.Consumer;

public class StaticLinkedGraphAdapter implements Graph<StaticLinkedGraph.Node, StaticLinkedGraph.Node> {

    private final StaticLinkedGraph graph;

    public StaticLinkedGraphAdapter(int vertexCount) {
        graph = new StaticLinkedGraph(vertexCount);
    }

    public StaticLinkedGraphAdapter(StaticLinkedGraph graph) {
        this.graph = graph;
    }

    @Override
    public boolean addNode(StaticLinkedGraph.Node v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addEdge(StaticLinkedGraph.Node v1, StaticLinkedGraph.Node v2) {
        return graph.addEdge(v1.id, v2.id);
    }

    @Override
    public StaticLinkedGraph.Node firstVertex(StaticLinkedGraph.Node v) {
        return graph.vNodes.get(v.id);
    }

    @Override
    public StaticLinkedGraph.Node nextVertex(StaticLinkedGraph.Node root, StaticLinkedGraph.Node cur) {
        return cur.next;
    }

    @Override
    public List<StaticLinkedGraph.Node> getVertexes() {
        return new ArrayList<>(graph.vNodes);
    }

    @Override
    public List<StaticLinkedGraph.Node> getEdges(StaticLinkedGraph.Node v) {
        throw new UnsupportedOperationException();
    }

    public void bfs(int vid, Consumer<StaticLinkedGraph.Node> consumer) {
        bfs(graph.vNodes.get(vid), consumer);
    }

    @Override
    public void bfs(StaticLinkedGraph.Node root, Consumer<StaticLinkedGraph.Node> consumer) {
        LinkedList<StaticLinkedGraph.Node> queue = new LinkedList<>();
        ArrayList<StaticLinkedGraph.Node> vNodes = graph.vNodes;
        boolean[] visits = new boolean[vNodes.size()];
        int idx = root.id, length = visits.length;
        boolean notFull = true;
        while (notFull) {
            queue.add(vNodes.get(idx));
            while (!queue.isEmpty()) {
                for (StaticLinkedGraph.Node cur = firstVertex(queue.pollFirst());
                     cur != null;
                     cur = nextVertex(null, cur)) {
                    int id = cur.id;
                    if (!visits[id]) {
                        consumer.accept(cur);
                        visits[id] = true;
                        queue.addLast(cur);
                    }
                }
            }
            notFull = false;
            for (idx = 1; idx < length; idx++)
                if (!visits[idx]) {
                    notFull = true;
                    break;
                }
        }
    }

    public void dfs(int vid, Consumer<StaticLinkedGraph.Node> consumer) {
        dfs(graph.vNodes.get(vid), consumer);
    }

    @Override
    public void dfs(StaticLinkedGraph.Node root, Consumer<StaticLinkedGraph.Node> consumer) {

    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ArrayList<StaticLinkedGraph.Node> vNodes = graph.vNodes;
        int size = vNodes.size();
        for (int i = 1; i < size; i++) {
            StaticLinkedGraph.Node cur = vNodes.get(i);
            StaticLinkedGraph.loadStringBuilder(ret, cur);
        }
        return ret.toString();
    }

}
