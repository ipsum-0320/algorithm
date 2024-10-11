package Java.hot_100;

import java.util.HashMap;
import java.util.Map;

public class _76 {

    public static void main(String[] args) {
        minWindow("ab", "b");
    }

    public static String minWindow(String s, String t) {
        int match = 0;
        // 记录窗口内满足了多少变量。
        String res = "";
        Map<Character, Integer> target = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            target.put(t.charAt(i), target.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (target.containsKey(s.charAt(i))) {
                // 如果包括了这个字符。
                target.put(s.charAt(i), target.get(s.charAt(i)) - 1);
                if (target.get(s.charAt(i)) == 0) {
                    // 可以一直减，但是只有为 0 时才需要让 match++。
                    match++;
                    if (match == target.keySet().size()) {
                        // 窗口已经包含了所有的字符。
                        if (res.isEmpty()) {
                            res = s.substring(left, i + 1);
                        }

                        while (left <= i && match == target.keySet().size()) {
                            // 开始挪动左指针，left == i 表示最右端的字符也可以被丢弃。
                            res = res.length() < i - left + 1 ? res : s.substring(left, i + 1);
                            if (target.containsKey(s.charAt(left))) {
                                target.put(s.charAt(left), target.get(s.charAt(left)) + 1);
                                if (target.get(s.charAt(left)) > 0) {
                                    match--;
                                }
                            }
                            left++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
