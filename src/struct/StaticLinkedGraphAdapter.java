package struct;

import java.util.*;
import java.util.function.BiConsumer;
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
        return graph.vNodes.get(v.id).next;
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

    public void bfs(int vid, BiConsumer<StaticLinkedGraph.Node, Integer> biConsumer) {
        bfs(graph.vNodes.get(vid), biConsumer);
    }

    @Override
    public void bfs(StaticLinkedGraph.Node root, BiConsumer<StaticLinkedGraph.Node, Integer> biConsumer) {
        final StaticLinkedGraph.Node LAYER_FLAG = new StaticLinkedGraph.Node(0);
        LinkedList<StaticLinkedGraph.Node> queue = new LinkedList<>();
        ArrayList<StaticLinkedGraph.Node> vNodes = graph.vNodes;
        boolean[] visits = new boolean[vNodes.size()];
        int idx = root.id, length = visits.length;
        boolean notFull = true;
        while (notFull) {
            int layer = 0;
            StaticLinkedGraph.Node r = vNodes.get(idx);
            visits[r.id] = true;
            biConsumer.accept(r, layer++);
            queue.addLast(vNodes.get(idx));
            queue.addLast(LAYER_FLAG);
            while (!queue.isEmpty()) {
                StaticLinkedGraph.Node poll = queue.pollFirst();
                if (poll == LAYER_FLAG) {
                    layer++;
                    poll = queue.pollFirst();
                    if (poll == null)
                        break;
                    queue.addLast(LAYER_FLAG);
                }
                for (StaticLinkedGraph.Node cur = firstVertex(poll);
                     cur != null;
                     cur = nextVertex(null, cur)) {
                    int id = cur.id;
                    if (!visits[id]) {
                        visits[id] = true;
                        biConsumer.accept(cur, layer);
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
