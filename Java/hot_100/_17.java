package Java.hot_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _17 {
    Map<String, List<String>> map = new HashMap<>();
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        map.put("2", List.of("a", "b", "c"));
        map.put("3", List.of("d", "e", "f"));
        map.put("4", List.of("g", "h", "i"));
        map.put("5", List.of("j", "k", "l"));
        map.put("6", List.of("m", "n", "o"));
        map.put("7", List.of("p", "q", "r", "s"));
        map.put("8", List.of("t", "u", "v"));
        map.put("9", List.of("w", "x", "y", "z"));

        helper(digits, 0, new StringBuilder());
        return res;
    }

    public void helper(String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            if (!sb.toString().isEmpty())
                res.add(sb.toString());
            return;
        }
        // 不要在循环内使用 String += String，直接使用 StringBuilder 就可。
        String key = digits.substring(index, index + 1);
        for (String s : map.get(key)) {
            sb.append(s);
            helper(digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
