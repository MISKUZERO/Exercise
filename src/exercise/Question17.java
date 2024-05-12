package exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class Question17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int num = Integer.parseInt(scanner.nextLine());
        String[] split = line.split(", ");
        ArrayList<Integer> cpus = new ArrayList<>();
        for (String s : split) {
            if (s.charAt(0) == '[') {
                cpus.add(Integer.parseInt(s.substring(1)));
            } else if (s.charAt(s.length() - 1) == ']') {
                cpus.add(Integer.parseInt(s.substring(0, s.length() - 1)));
            } else {
                cpus.add(Integer.parseInt(s));
            }
        }
        ArrayList<Integer> link1 = new ArrayList<>();
        ArrayList<Integer> link2 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (Integer integer : cpus) {
            if (integer < 4) {
                link1.add(integer);
            } else
                link2.add(integer);
        }
        if (num == 1) {
            if (link1.size() == 1) {
                while (!link1.isEmpty()) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(link1.remove(0));
                    res.add(list);
                }
            } else if (link2.size() == 1) {
                while (!link2.isEmpty()) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(link2.remove(0));
                    res.add(list);
                }
            }
        } else if (num == 2) {
            //...
        } else if (num == 4) {
            //...
        } else if (num == 8) {
            //...
        }
        //...
    }
}
