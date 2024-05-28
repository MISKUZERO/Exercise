package exercise.ex;

import java.util.ArrayList;

public class IsValid {

    public boolean isValid(String s) {
        ArrayList<Integer> stack = new ArrayList<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add(1);
            } else if (c == '[') {
                stack.add(2);
            } else if (c == '{') {
                stack.add(3);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.remove(stack.size() - 1) != 1)
                    return false;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.remove(stack.size() - 1) != 2)
                    return false;
            } else {
                if (stack.isEmpty() || stack.remove(stack.size() - 1) != 3)
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
