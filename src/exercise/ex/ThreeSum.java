package exercise.ex;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        int[] ints = new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        System.out.println(new ThreeSum().threeSumNew(ints));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    int a = nums[i], b = nums[j], c = nums[k];
                    if (a + b + c == 0) {
                        ArrayList<Integer> list = new ArrayList<>() {

                            @Override
                            public int hashCode() {
                                return get(0) ^ get(1) ^ get(2);
                            }

                            @Override
                            @SuppressWarnings("all")
                            public boolean equals(Object o) {
                                ArrayList<Integer> tmp = new ArrayList<>((ArrayList<Integer>) o);
                                for (Integer integer : this)
                                    if (!tmp.remove(integer))
                                        return false;
                                return true;
                            }
                        };
                        list.add(a);
                        list.add(b);
                        list.add(c);
                        set.add(list);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    public List<List<Integer>> threeSumNew(int[] nums) {
        HashSet<ArrayList<Integer>> res = new HashSet<>();
        HashSet<Integer> visits = new HashSet<>();
        HashMap<Integer, Integer> set = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int a = nums[i];
            if (visits.add(a)) {
                for (int j = 0; j < length; j++) {
                    if (i == j) continue;
                    int b = nums[j], c = -b - a;
                    Integer k = set.get(c);
                    if (k == null) {
                        set.put(b, j);
                    } else {
                        if (k == i || k == j) continue;
                        ArrayList<Integer> list = new ArrayList<>() {
                            @Override
                            public int hashCode() {
                                return get(0) ^ get(1) ^ get(2);
                            }

                            @Override
                            @SuppressWarnings("all")
                            public boolean equals(Object o) {
                                ArrayList<Integer> tmp = new ArrayList<>((ArrayList<Integer>) o);
                                for (Integer integer : this)
                                    if (!tmp.remove(integer))
                                        return false;
                                return true;
                            }
                        };
                        list.add(a);
                        list.add(b);
                        list.add(c);
                        res.add(list);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

}
