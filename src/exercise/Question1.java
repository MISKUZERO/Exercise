package exercise;

import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] ranges = new int[n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                ranges[i][j] = scanner.nextInt();
        int max = 1;
        for (int[] range : ranges) {
            int l = range[0];
            int r = range[1];
            int sum = countSingle(l, n, ranges);
            if (sum > max)
                max = sum;
            sum = countSingle(r, n, ranges);
            if (sum > max)
                max = sum;
        }
        System.out.println("最少需要的服务器数量：" + max);
    }

    private static boolean isInRange(int l, int r, int num) {
        return l <= num && num <= r;
    }

    public static int countSingle(int num, int n, int[][] ranges) {
        int sum = 0;
        for (int j = 0; j < n; j++)
            if (isInRange(ranges[j][0], ranges[j][1], num))
                sum += ranges[j][2];
        return sum;
    }
}
