package exercise;

import java.util.Scanner;

public class Question90 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line) + 1;
        int[][] vs = new int[n][n];
        for (int i = 1; i < n; i++) {
            String line1 = scanner.nextLine();
            String[] s = line1.split(" ");
            vs[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = 1;
        }
        String line1 = scanner.nextLine();
        int id = Integer.parseInt(line1);
        int send = -1, L = -1, receive = -1;
        for (int i = 1; i < n; i++) {
            if (vs[id][i] == 1)
                send++;
            if (vs[i][id] == 1)
                receive++;
            else
                L++;
        }
        int M = send - receive;
        int ax = -1, xa = -1;
        for (int x = 1; x < n; x++) {
            for (int i = 1; i < 100; i++) {
                if (vs[id][x] == 1)
                    ax++;
                if (vs[x][id] == 1)
                    xa++;
            }
        }
        int N = ax - xa;
        if (L > 5 || M > 10 || N > 5) {
            System.out.println(true + " " + L + " " + M);
        } else {
            System.out.println(false + " " + L + " " + M);
        }
    }
}
