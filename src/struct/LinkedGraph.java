package struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class LinkedGraph implements Graph<Integer> {

    static class Node {
        Node next;
        int id;
        boolean red;

        Node(int id) {
            this.id = id;
        }
    }

    ArrayList<Node> vNodes;
    HashMap<Integer, ArrayList<Node>> caches;

    public LinkedGraph(int vertexCount) {
        ArrayList<Node> vNodes = new ArrayList<>();
        for (int i = 0; i <= vertexCount; i++)
            vNodes.add(new Node(i));
        this.vNodes = vNodes;
        this.caches = new HashMap<>();
    }

    @Override
    public boolean addNode(Integer node) {
        return false;
    }

    @Override
    public boolean addEdge(Integer node1, Integer node2) {
        //不检查重复添加边，头插法
        HashMap<Integer, ArrayList<Node>> caches = this.caches;
        Node n1 = new Node(node1);
        n1.next = vNodes.get(node2).next;
        vNodes.get(node2).next = n1;
        caches.computeIfAbsent(node1, k -> new ArrayList<>()).add(n1);
        //对应边添加
        Node n2 = new Node(node2);
        n2.next = vNodes.get(node1).next;
        vNodes.get(node1).next = n2;
        caches.computeIfAbsent(node2, k -> new ArrayList<>()).add(n2);
        return true;
    }

    @Override
    public Integer firstNode() {
        return null;
    }

    @Override
    public Integer nextNode(Integer cur) {
        return null;
    }

    @Override
    public List<Integer> getAll() {
        return null;
    }

    @Override
    public void bfs(Consumer<Integer> consumer) {

    }

    @Override
    public void dfs(Consumer<Integer> consumer) {

    }

    public void paint(int[] vIds, int size, Boolean red) {
        HashMap<Integer, ArrayList<Node>> caches = this.caches;
        ArrayList<Node> vNodes = this.vNodes;
        for (int i = 0; i < size; i++) {
            int id = vIds[i];
            vNodes.get(id).red = red;
            caches.forEach((vid, nodes) -> {
                if (vid == id)
                    for (Node node : nodes)
                        node.red = red;
            });
        }
    }

    public boolean test() {
        ArrayList<Node> vNodes = this.vNodes;
        int size = vNodes.size();
        for (int i = 1; i < size; i++)
            if (vNodes.get(i).red) {
                Node cur = vNodes.get(i).next;
                while (cur != null) {
                    if (cur.red)
                        return false;
                    cur = cur.next;
                }
            }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ArrayList<Node> vNodes = this.vNodes;
        int size = vNodes.size();
        for (int i = 1; i < size; i++) {
            Node cur = vNodes.get(i);
            while (cur != null) {
                if (cur.red)
                    ret.append("(").append(cur.id).append(")").append("->");
                else
                    ret.append(" ").append(cur.id).append(" ").append("->");
                cur = cur.next;
            }
            ret.append("\b\b\n");
        }
        return ret.toString();
    }
}
