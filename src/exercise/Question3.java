package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Question3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int M = scanner.nextInt();
        final int n = scanner.nextInt();
        ArrayList<Integer> ms = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ms.add(scanner.nextInt());
        Collections.sort(ms);
        int count = 0;
        int i = 0, j = ms.size() - 1;
        while (i < j) {
            int sum = ms.get(i) + ms.get(j);
            if (sum <= M)
                i++;
            j--;
            count++;
        }
        if (i == j)
            count++;
        System.out.println("最小的自行车数量：" + count);

    }

}
