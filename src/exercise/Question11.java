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
        dfs(X, 0, M, N, 0, 0, X, new ArrayList<>(), paths);
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
        for (ArrayList<ArrayList<Integer>> path : paths)
            if (path.size() == min)
                System.out.println(path);
        System.out.println(min);
    }

    public static void dfs(int s, int w, int rs, int rw, int os, int ow, final int C,
                           ArrayList<ArrayList<Integer>> path, ArrayList<ArrayList<ArrayList<Integer>>> paths) {
        if ((s != 0 && s <= w) || (rs != 0 && rs <= rw) || (os != 0 && os <= ow) || (rs < 0 || rw < 0)) return;
        if (rs == 0 && rw == 0) {
            paths.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i <= C; i++)
            for (int j = i; j <= C; j++)
                if (j != 0) handle(rs, rw, i, j - i, os, ow, C, path, paths);
    }

    private static void handle(int rs, int rw, int ss, int ww, int os, int ow, final int C,
                               ArrayList<ArrayList<Integer>> path, ArrayList<ArrayList<ArrayList<Integer>>> paths) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(ss);
        list.add(ww);
        path.add(list);
        dfs(ss, ww, rs - ss, rw - ww, os + ss, ow + ww, C, path, paths);
        path.remove(path.size() - 1);
    }
}
