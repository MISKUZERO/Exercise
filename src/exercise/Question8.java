package exercise;

import java.util.Scanner;

public class Question8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] colors = line.split(" ");
        int length = colors.length;
        int[] cs = new int[length];
        for (int i = 0; i < length; i++)
            cs[i] = Integer.parseInt(colors[i]);
        int wLen = scanner.nextInt();

        int maxCount = 0;
        int len = colors.length - wLen + 1;
        for (int i = 0; i < len; i++) {
            int end = i + wLen;
            int[] ints = new int[3];
            for (int j = i; j < end; j++)
                ints[cs[j]]++;
            for (int integer : ints)
                if (integer > maxCount)
                    maxCount = integer;
        }
        System.out.println(maxCount);
    }
}
