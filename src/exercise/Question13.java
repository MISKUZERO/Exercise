package exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Question13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        String exclude = scanner.nextLine();
        String[] dictionary = {
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqr",
                "st",
                "uv",
                "wx",
                "yz"
        };
        ArrayList<Integer> nums = new ArrayList<>();
        int length = num.length();
        for (int i = 0; i < length; i++) {
            int n = Integer.parseInt(num.substring(i, i + 1));
            nums.add(n);
        }
        ArrayList<String> strings = new ArrayList<>();
        int[] bits = new int[length];
        do {
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            for (Integer integer : nums) {
                char c = dictionary[integer].charAt(bits[i++]);
                stringBuilder.append(c);
            }
            String s = stringBuilder.toString();
            if (!s.contains(exclude))
                strings.add(s);
            inc(bits, dictionary, nums);
        } while (!circle(bits));
        for (String string : strings) {
            System.out.print(string + " ");
        }
    }

    public static void inc(int[] bits, String[] dictionary, ArrayList<Integer> nums) {
        bits[0]++;
        int length = bits.length;
        for (int i = 0; i < length; i++) {
            if (bits[i] == dictionary[nums.get(i)].length()) {
                bits[i] = 0;
                if (i + 1 < length)
                    bits[i + 1]++;//进位
            }
        }
    }

    public static boolean circle(int[] bits) {
        for (int bit : bits) {
            if (bit != 0)
                return false;
        }
        return true;
    }
}
