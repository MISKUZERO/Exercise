package exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Question70 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int money = Integer.parseInt(line);
        String line1 = scanner.nextLine();
        String[] split = line1.split(",");
        ArrayList<Integer> costs = new ArrayList<>();
        for (String s : split) {
            costs.add(Integer.parseInt(s));
        }
        HashSet<ArrayList<Integer>> res = new HashSet<>();
        int size = costs.size();
        int[] num = new int[size];
        do {
            ArrayList<Integer> costList = new ArrayList<>() {
                @Override
                public boolean equals(Object o) {
                    if (o instanceof ArrayList) {
                        ArrayList<Integer> arrayList = (ArrayList<Integer>) o;
                        if (arrayList.size() == this.size()) {
                            return new HashSet<>(this).equals(new HashSet<>(arrayList));
                        } else
                            return false;
                    } else
                        return false;
                }

                @Override
                public int hashCode() {
                    int hash = 0;
                    for (Integer integer : this)
                        hash += integer;
                    return hash;
                }
            };
            int sum = 0;
            for (int i = 0; i < size; i++) {
                Integer cost = costs.get(num[i]);
                sum += cost;
                costList.add(cost);
                if (sum > money)
                    break;
                if (sum == money) {
                    res.add(costList);
                    break;
                }
            }
            //迭代
            num[0]++;
            for (int i = 0; i < size; i++) {
                if (num[i] == size) {
                    num[i] = 0;
                    if (i + 1 < size)
                        num[i + 1]++;
                }
            }
        } while (!circle(num));
        System.out.println(res);
    }

    public static boolean circle(int[] nums) {
        for (int num : nums)
            if (num != 0)
                return false;
        return true;
    }
}
