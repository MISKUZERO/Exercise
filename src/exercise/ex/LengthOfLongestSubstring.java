package exercise.ex;

import java.util.HashMap;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("aab"));
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

}
