import java.util.ArrayList;

public class Test4 {

    static int v = 30, e = 90;
    static ArrayList<int[]> edges = getEdges(v, e);

    public static ArrayList<int[]> getEdges(int V, int E) {
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
//        Question4.main(null);
        Question4E.main(null);
        Question4EX.main(null);
    }
}
