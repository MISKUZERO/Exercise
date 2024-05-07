package exercise;

import java.util.ArrayList;

public class GraphUtil {

    static int v = 25, e = 75;
    public static ArrayList<int[]> edges = getEdges(v, e);

    public static ArrayList<int[]> getEdges(int V, int E) {
        if (E > (V * (V - 1) >> 1)) throw new IllegalArgumentException("超过最大边数！");
        ArrayList<int[]> ret = new ArrayList<>();
        for (int i = 0; i < E; ) {
            int v1 = (int) (Math.random() * V) + 1;
            int v2 = (int) (Math.random() * V) + 1;
            while (v2 == v1)
                v2 = (int) (Math.random() * V) + 1;
            boolean ex = true;
            for (int[] edge : ret) {
                int vv1 = edge[0], vv2 = edge[1];
                if ((v1 == vv1 && v2 == vv2) || (v1 == vv2 && v2 == vv1)) {
                    ex = false;
                    break;
                }
            }
            if (ex) {
                ret.add(new int[]{v1, v2});
                i++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Question4.main(null);
        Question4E.main(null);
        Question4EX.main(null);
    }
}
