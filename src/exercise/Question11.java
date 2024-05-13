package exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Question11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] s = line.split(" ");
        final int M = Integer.parseInt(s[0]);
        final int N = Integer.parseInt(s[1]);
        final int X = Integer.parseInt(s[2]);
        ArrayList<ArrayList<ArrayList<Integer>>> paths = new ArrayList<>();
        dfs(X, 0, M, N, X, new ArrayList<>(), paths);
        if (paths.isEmpty()) {
            System.out.println(0);
            return;
        }
        int min = paths.get(0).size();
        for (ArrayList<ArrayList<Integer>> path : paths) {
            int size = path.size();
            if (size < min)
                min = size;
        }
        for (ArrayList<ArrayList<Integer>> path : paths) {
            int size = path.size();
            if (size == min)
                System.out.println(path);
        }
        System.out.println(paths.size());
        System.out.println(min);
    }


    public static void dfs(int s, int w, int rs, int rw, final int C,
                           ArrayList<ArrayList<Integer>> path, ArrayList<ArrayList<ArrayList<Integer>>> paths) {
        if (w > s || rw > rs || rs < 0 || rw < 0)
            return;
        if (rs == 0) {
            paths.add(new ArrayList<>(path));
            return;
        }
        doDFS(0, new int[C], rs, rw, C, path, paths);
    }

    private static void doDFS(int index, int[] nums,
                              int rs, int rw, final int C,
                              ArrayList<ArrayList<Integer>> path, ArrayList<ArrayList<ArrayList<Integer>>> paths) {
        if (index == C) {//处理
            int ss = 0, ww = 0;
            for (int num : nums)
                if (num == 1)
                    ss++;
                else if (num == 2)
                    ww++;
            if (ss + ww == 0) return;
            ArrayList<Integer> list = new ArrayList<>();
            list.add(ss);
            list.add(ww);
            path.add(list);
            dfs(ss, ww, rs - ss, rw - ww, C, path, paths);
            path.remove(path.size() - 1);
            return;
        }
        for (int i = 0; i < 3; i++) {
            nums[index++] = i;
            doDFS(index--, nums, rs, rw, C, path, paths);
        }

    }
}
