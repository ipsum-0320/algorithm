package Java.hot_100;

import java.util.Stack;

public class _20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int cur = 0;
        while (cur != s.length()) {
            if (s.charAt(cur) == '(' || s.charAt(cur) == '[' || s.charAt(cur) == '{') {
                stack.push(s.charAt(cur));
            } else {
                if (s.charAt(cur) == ')') {
                    if (stack.isEmpty()) return false;
                    char peek = stack.pop();
                    if (peek != '(') return false;
                } else if (s.charAt(cur) == ']') {
                    if (stack.isEmpty()) return false;
                    char peek = stack.pop();
                    if (peek != '[') return false;
                } else {
                    if (stack.isEmpty()) return false;
                    char peek = stack.pop();
                    if (peek != '{') return false;
                }
            }
            cur++;
        }
        return stack.isEmpty();
    }

}
