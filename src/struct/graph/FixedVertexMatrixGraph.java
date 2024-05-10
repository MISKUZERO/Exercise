package struct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FixedVertexMatrixGraph implements Graph<Integer, int[]> {

    private final int[][] vertexes;
    private final ArrayList<int[]> edges;

    public FixedVertexMatrixGraph(int vertexCount) {
        int size = vertexCount + 1;
        vertexes = new int[size][size];
        edges = new ArrayList<>();
    }

    @Override
    public boolean addNode(Integer v) {
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

    @Override
    public int[][] singleShortestRoute(Integer root) {
        int[][] vertexes = this.vertexes;
        int length = vertexes.length;
        boolean[] finals = new boolean[length], templates = new boolean[length];
        int[] costs = new int[length], paths = new int[length];
        initData(root, templates, costs);
        while (!Arrays.equals(finals, templates))
            updateData(vertexes, finals, costs, paths,
                    getCurMinCostVertex(finals, costs, root));
        return new int[][]{costs, paths};
    }

    @Override
    public int[][][] allShortestRoute() {
        int[][] vertexes = this.vertexes;
        int length = vertexes.length;
        int[][] paths = new int[length][length], costs = new int[length][length];
        //初始化
        for (int i = 1; i < length; i++)
            for (int j = 1; j < length; j++)
                if (i != j) {
                    int cost = vertexes[i][j];
                    if (cost == 0)
                        costs[i][j] = Integer.MAX_VALUE;
                    else
                        costs[i][j] = cost;
                }
        //执行
        for (int k = 1; k < length; k++) {
            for (int i = 1; i < length; i++) {
                for (int j = 1; j < length; j++) {
                    int cIK = costs[i][k], cKJ = costs[k][j], cIJ = costs[i][j], cIKJ = cIK + cKJ;
                    if (cIK == Integer.MAX_VALUE || cKJ == Integer.MAX_VALUE)//规格化
                        cIKJ = Integer.MAX_VALUE;
                    if (cIKJ < cIJ) {
                        costs[i][j] = cIKJ;
                        paths[i][j] = k;
                    }
                }
            }
        }
        return new int[][][]{costs, paths};
    }

    @Override
    public Graph<Integer, int[]> minimumSpanningTree(Integer root) {
        int[][] vertexes = this.vertexes;
        int length = vertexes.length;
        FixedVertexMatrixGraph graph = new FixedVertexMatrixGraph(length - 1);//构建生成树
        boolean[] finals = new boolean[length], templates = new boolean[length];
        int[] costs = new int[length], paths = new int[length];
        initData(root, templates, costs);
        while (!Arrays.equals(finals, templates)) {
            int cur = getCurMinCostVertex(finals, costs, root);
            int pre = paths[cur];
            graph.addEdge(pre, cur, vertexes[pre][cur], true);//构建生成树
            updateData(vertexes, finals, costs, paths, cur);
        }
        return graph;
    }

    private void initData(int root, boolean[] templates, int[] costs) {
        doDFS(root, v -> {
        }, templates);//检查与include节点连通的所有顶点
        int length = costs.length;
        for (int i = 1; i < length; i++)
            costs[i] = Integer.MAX_VALUE;
        costs[root] = 0;
    }

    private void updateData(int[][] vertexes, boolean[] finals, int[] costs, int[] paths, int cur) {
        for (int next = firstVertex(cur); next != -1; next = nextVertex(cur, next))
            if (!finals[next]) {
                int cost = costs[cur] + vertexes[cur][next];
                if (cost < costs[next]) {
                    costs[next] = cost;
                    paths[next] = cur;
                }
            }
    }

    private static int getCurMinCostVertex(boolean[] finals, int[] costs, int root) {
        int minCost = Integer.MAX_VALUE;
        int length = finals.length;
        for (int i = 1; i < length; i++)
            if (!finals[i]) {
                int cost = costs[i];
                if (cost < minCost) {
                    root = i;
                    minCost = cost;
                }
            }
        finals[root] = true;
        return root;
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
