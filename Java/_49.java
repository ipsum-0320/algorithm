package Java;

import java.util.*;

public class _49 {
    public static void main(String[] args) {

    }
    // 排序方法。
    public List<List<String>> groupAnagrams(String[] strs) {
        // Java 值传递：https://segmentfault.com/a/1190000021529503。
        // 要与 Go 的传递机制分别开：https://jaycechant.info/2021/golang-in-action-day-9-pointer-reference-and-value/。
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (map.containsKey(sorted)) {
                List<String> strings = map.get(sorted);
                strings.add(str);
            } else {
                List<String> strings = new ArrayList<>();
                strings.add(str);
                map.put(sorted, strings);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet()) {
            List<String> list = new ArrayList<>(map.get(key));
            res.add(list);
        }
        return res;
    }
}
