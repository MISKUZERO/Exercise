package exercise.ex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstringNew2(""));
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
        int[] codes = new int[128];
        Arrays.fill(codes, -1);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            curCount = Math.min(curCount + 1, i - codes[c]);
            count = Math.max(count, curCount);
            codes[c] = i;
        }
        return count;
    }


}
