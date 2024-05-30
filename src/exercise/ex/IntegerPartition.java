package exercise.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class IntegerPartition {

    public static void main(String[] args) {
        final int N = 15;
        System.out.println(Arrays.toString(p(N)));
        System.out.print("[");
        for (int i = 0; i <= N; i++)
            System.out.print(pForEach(i) + ", ");
        System.out.println("\b\b]");
    }

    public static long[] p(int n) {
        long[] p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; ++i)
            for (int j = i; j <= n; ++j)
                p[j] += p[j - i];
        return p;
    }

//    public static long p(int n) {
//        if (n == 0) return 1;
//        int count = 0;
//        for (int i = n; i > 0; i--) {
//            int[] nums = new int[n];
//            int size = 0, re = n;
//            while (re > 0) {
//                nums[size++] = Math.min(i, re);
//                re -= i;
//            }
//            count++;
//            System.out.println(Arrays.toString(nums));
//            for (int j = size - 1; j != 0; j--) {
//                if (nums[j] != 1) {
//                    nums[j]--;
//                    int num = nums[j];
//                    int k = j + 1;
//                    while (nums[k] == num) k++;
//                    if (nums[k] == 0) size++;
//                    nums[k]++;
//                    j = size;
//                    count++;
//                    System.out.println(Arrays.toString(nums));
//                }
//            }
//        }
//        return count;
//    }

    public static long pForEach(int n) {
        HashSet<ArrayList<Integer>> paths = new HashSet<>();
        ArrayList<Integer> path = new ArrayList<>() {

            private int sum;

            @Override
            public boolean add(Integer integer) {
                sum += integer;
                return super.add(integer);
            }

            @Override
            public Integer remove(int index) {
                sum -= get(index);
                return super.remove(index);
            }

            @Override
            public int hashCode() {
                return sum;
            }

        };
        dfs(0, n, path, paths);
        return paths.size();
    }

    private static void dfs(int idx, int n, ArrayList<Integer> path, HashSet<ArrayList<Integer>> paths) {
        int sum = path.hashCode();
        if (sum > n)
            return;
        if (idx == n) {
            if (sum == n && paths.add(new ArrayList<>(path) {
                @Override
                public int hashCode() {
                    int hash = 0;
                    for (Integer integer : this)
                        hash ^= integer * 29;
                    return hash;
                }

                @Override
                public boolean equals(Object o) {
                    if (o instanceof ArrayList) {
                        ArrayList<Integer> list = (ArrayList<Integer>) o;
                        int[] counts = new int[n + 1];
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            counts[get(i)]++;
                            counts[list.get(i)]--;
                        }
                        for (int count : counts)
                            if (count != 0)
                                return false;
                        return true;
                    }
                    return false;
                }
            })) ;
//                System.out.println(path);
            return;
        }
        for (int j = 0; j <= n; j++) {
            path.add(j);
            dfs(idx + 1, n, path, paths);
            path.remove(path.size() - 1);
        }
    }
}
