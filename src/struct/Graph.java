package struct;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    static class Node {
        Node next;
        int id;
        boolean red;

        Node(int id) {
            this.id = id;
        }
    }

    Node[] vNodes;

    HashMap<Integer, ArrayList<Node>> caches;

    public Graph(int vCount) {
        Node[] vNodes = new Node[vCount + 1];
        for (int i = 1; i <= vCount; i++)
            vNodes[i] = new Node(i);
        this.vNodes = vNodes;
        this.caches = new HashMap<>();
    }

    public void addEdge(int v1, int v2) {
        //不检查重复添加边，头插法
        HashMap<Integer, ArrayList<Node>> caches = this.caches;
        Node n1 = new Node(v1);
        n1.next = vNodes[v2].next;
        vNodes[v2].next = n1;
        caches.computeIfAbsent(v1, k -> new ArrayList<>()).add(n1);
        //对应边添加
        Node n2 = new Node(v2);
        n2.next = vNodes[v1].next;
        vNodes[v1].next = n2;
        caches.computeIfAbsent(v2, k -> new ArrayList<>()).add(n2);
    }

    public void paint(int[] vIds, int size, Boolean red) {
        HashMap<Integer, ArrayList<Node>> caches = this.caches;
        Node[] vNodes = this.vNodes;
        for (int i = 0; i < size; i++) {
            int id = vIds[i];
            vNodes[id].red = red;
            caches.forEach((vid, nodes) -> {
                if (vid == id)
                    for (Node node : nodes)
                        node.red = red;
            });
        }
    }

    public boolean test() {
        Node[] vNodes = this.vNodes;
        int length = vNodes.length;
        for (int i = 1; i < length; i++)
            if (vNodes[i].red) {
                Node cur = vNodes[i].next;
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
        Node[] vNodes = this.vNodes;
        int length = vNodes.length;
        for (int i = 1; i < length; i++) {
            Node cur = vNodes[i];
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
