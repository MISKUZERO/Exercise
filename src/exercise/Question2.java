package exercise;

import java.util.*;

public class Question2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] words = line.split(" ");
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            Arrays.sort(chars);
            words[i] = new String(chars);
        }
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (String word : words) {
            List<Integer> list = map.computeIfAbsent(word, s -> new ArrayList<>());
            list.add(1);
        }

        Set<Map.Entry<String, List<Integer>>> entries = map.entrySet();
        ArrayList<Map.Entry<String, List<Integer>>> list = new ArrayList<>(entries);
        list.sort((m1, m2) -> {
            int s1l = m1.getValue().size();
            int s2l = m2.getValue().size();
            if (s1l < s2l)
                return 1;
            else if (s1l > s2l)
                return -1;
            else {
                String s1 = m1.getKey();
                String s2 = m2.getKey();
                int sl1 = s1.length();
                int sl2 = s2.length();
                if (sl1 < sl2)
                    return -1;
                else if (sl1 > sl2)
                    return 1;
                else
                    return s1.compareTo(s2);
            }
        });
        for (Map.Entry<String, List<Integer>> stringListEntry : list) {
            int size = stringListEntry.getValue().size();
            for (int j = 0; j < size; j++)
                System.out.print(stringListEntry.getKey() + " ");
        }

    }
}
