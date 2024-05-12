package exercise;

import java.util.Arrays;
import java.util.Scanner;

public class Question55 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = scanner.nextInt();
        }
        Arrays.sort(distances);
        int max = distances[n - 1], min = distances[0];
        int minSum = Integer.MAX_VALUE, index = max;
        for (int i = max; i >= min; i--) {
            int sum = 0;
            for (int distance : distances)
                sum += Math.abs(distance - i);
            if (sum < minSum) {
                minSum = sum;
                index = i;
            }
        }

        System.out.println(index);
    }
}
