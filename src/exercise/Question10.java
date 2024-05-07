package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Question10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] s = line.split(" ");
        Arrays.sort(s);
        System.out.println(Arrays.toString(s));
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        ArrayList<String> sList = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            String cur = s[i];
            if (i + 1 == s.length) {
                sList.add(cur);
                ret.add(sList);
                break;
            }
            String next = s[i + 1];
            sList.add(cur);
            if (!next.contains(cur)) {
                ret.add(sList);
                sList = new ArrayList<>();
            }
        }
        System.out.println(ret);
    }
}
