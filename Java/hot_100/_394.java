package Java.hot_100;

import java.util.LinkedList;

public class _394 {

    int ptr;

    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ptr++;
                // 这里只能将 12, 23, 34 翻转为 34, 23, 12，而不能是 43, 32, 21。
                LinkedList<String> list = new LinkedList<>();
                while (!"[".equals(stk.peekLast())) {
                    list.add(0, stk.removeLast());
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    sb.append(list.get(i));
                }
                String str = sb.toString();

                stk.removeLast();
                int num = Integer.parseInt(stk.removeLast());

                sb.delete(0, sb.length());
                for (int i = 0; i < num; i++) {
                    sb.append(str);
                }
                stk.addLast(sb.toString());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stk.size(); i++) {
            sb.append(stk.get(i));
        }
        return sb.toString();
    }

    public String getDigits(String s) {
        StringBuilder ret = new StringBuilder();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

}
