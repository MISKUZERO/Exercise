package exercise.ex;

public class CompressString {

    public String compressString(String S) {
        if (S.length() == 0) return S;
        int length = S.length();
        char c = S.charAt(0);
        int count = 1;
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < length; i++) {
            char cur = S.charAt(i);
            if (cur == c)
                count++;
            else {
                res.append(c).append(count);
                count = 1;
                c = cur;
            }
        }
        res.append(c).append(count);
        return res.length() < S.length() ? res.toString() : S;
    }
}
