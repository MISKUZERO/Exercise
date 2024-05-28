package exercise.ex;

import java.util.ArrayList;

public class ReverseParentheses {

    public static void main(String[] args) {
        String s = new ReverseParentheses().reverseParentheses("(u(love)i)");
        System.out.println(s);
    }

    public String reverseParentheses(String s) {
        ArrayList<Character> stack = new ArrayList<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                ArrayList<Character> tmp = new ArrayList<>();
                char cur;
                while ((cur = stack.remove(stack.size() - 1)) != '(')
                    tmp.add(cur);
                stack.addAll(tmp);
            } else
                stack.add(c);
        }
        StringBuilder res = new StringBuilder();
        for (Character character : stack)
            res.append(character);
        res.reverse();
        return res.toString();
    }
}
