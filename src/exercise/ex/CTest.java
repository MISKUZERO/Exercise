package exercise.ex;

public class CTest {
    public static void main(String[] args) {
        for (int i = 3; i < 10; i++)
            System.out.print(cN5(i) + "  ");
        System.out.println();
        for (int i = 0; i < 8; i++) {
            System.out.print(cNK(7, i, 0, 0) + ", ");
            System.out.println(cNK(7, i));
        }
    }

    public static int cN3(int n) {
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    count++;
        return count;
    }

    public static int cN4(int n) {
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    for (int l = k + 1; l < n; l++)
                        count++;
        return count;
    }

    public static int cN5(int n) {
        int count = 0;
        for (int i1 = 0; i1 < n; i1++)
            for (int i2 = i1 + 1; i2 < n; i2++)
                for (int i3 = i2 + 1; i3 < n; i3++)
                    for (int i4 = i3 + 1; i4 < n; i4++)
                        for (int i5 = i4 + 1; i5 < n; i5++)
                            count++;
        return count;
    }

    public static int cNK(int n, int k) {
        return cNK(n, k, 0, 0, new boolean[k + 1]);
    }

    private static int cNK(final int n, final int k, int layer, int count) {
        if (layer == k)
            return count;
        for (int i = layer; i < n; i++)
            count = cNK(n, k, layer + 1, count + 1);
        return count;
    }

    private static int cNK(final int n, final int k, int layer, int count, boolean[] visits) {
        if (visits[layer] || layer == k)
            return count;
        for (int i = layer; i < n; i++)
            count = cNK(n, k, layer + 1, count + 1, visits);
        visits[layer] = true;
        return count;
    }

}
