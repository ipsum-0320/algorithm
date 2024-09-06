package Java.hot_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _763 {

    public List<Integer> partitionLabels(String s) {
        // 划分字母区间，重点是记录每个字符在字符串中出现的最后位置。
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, map.get(s.charAt(i)));
            if (i == end) {
                res.add(end - start + 1);
                start = i + 1;
            }
        }
        return res;
    }

}
