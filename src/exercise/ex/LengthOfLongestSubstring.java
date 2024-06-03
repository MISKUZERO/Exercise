package exercise.ex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "bziuwnklhqzrxnb";
        LengthOfLongestSubstring lol = new LengthOfLongestSubstring();
        long l = System.nanoTime();
        int i = lol.lengthOfLongestSubstring(s);
        System.out.println(System.nanoTime() - l);

        l = System.nanoTime();
        int in = lol.lengthOfLongestSubstringNew(s);
        System.out.println(System.nanoTime() - l);

        l = System.nanoTime();
        int in1 = lol.lengthOfLongestSubstringNew1(s);
        System.out.println(System.nanoTime() - l);

        l = System.nanoTime();
        int in2 = lol.lengthOfLongestSubstringNew2(s);
        System.out.println(System.nanoTime() - l);

        l = System.nanoTime();
        int in3 = lol.lengthOfLongestSubstringNew3(s);
        System.out.println(System.nanoTime() - l);

        System.out.println(i);
        System.out.println(in);
        System.out.println(in1);
        System.out.println(in2);
        System.out.println(in3);
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            int count = 0;
            for (int j = i; j < length; j++)
                if (hashMap.putIfAbsent(s.charAt(j), 0) == null)
                    count++;
                else //重复
                    break;
            max = Math.max(max, count);
        }
        return max;
    }

    public int lengthOfLongestSubstringNew(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int max = 0;
        int length = s.length();
        for (int i = 0, j = 0; i < length; i++) {
            for (; j < length; j++)
                if (hashMap.putIfAbsent(s.charAt(j), 0) != null) //重复
                    break;
            max = Math.max(max, hashMap.size());
            hashMap.remove(s.charAt(i));
        }
        return max;
    }

    public int lengthOfLongestSubstringNew1(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        int maxLen = 0;
        int length = s.length();
        if (length == 0) return 0;
        char c = s.charAt(0);
        int i = 0, j = 0;
        while (i != length) {
            while (hashSet.contains(c))
                hashSet.remove(s.charAt(j++));
            while (i != length && hashSet.add((c = s.charAt(i)))) i++;
            maxLen = Math.max(maxLen, hashSet.size());
        }
        return maxLen;
    }

    public int lengthOfLongestSubstringNew2(String s) {
        int count = 0, curCount = 0;
        int length = s.length();
        int[] codes = new int[100000];
        Arrays.fill(codes, -1);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            curCount = Math.min(curCount + 1, i - codes[c]);
            count = Math.max(count, curCount);
            codes[c] = i;
        }
        return count;
    }

    public int lengthOfLongestSubstringNew3(String s) {
        int length = s.length();
        if (length == 0) return 0;
        return dfs(s, 0, length - 1);
    }

    static int dfs(String s, int l, int r) {
        if (l == r) return 1;
        int m = (l + r) >> 1;
        return Math.max(Math.max(dfs(s, l, m), dfs(s, m + 1, r)), m(s, l, m, r));
    }

    static int m(String s, int l, int m, int r) {
        // ...
        return 0;
    }

}
