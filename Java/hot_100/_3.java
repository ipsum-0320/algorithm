package Java.hot_100;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _3 {
    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口，双指针。
        // 排序/二分查找，双指针。
        int left = 0, right = 0;
        // 队列：Queue。
        // 双端队列：Deque。
        // 实现类：LinkedList。
        // 使用方法：add/remove/element，offer/poll/peek。
        Queue<Character> queue = new LinkedList<>();
        int maxL = 0;
        while (right < s.length()) {
            if (!queue.contains(s.charAt(right))) {
                queue.add(s.charAt(right));
                right++;
                maxL = Math.max(maxL, right - left);
            } else {
                while (queue.element() != s.charAt(right)) {
                    queue.remove();
                    left++;
                }
                queue.remove();
                left++;
            }
        }
        return maxL;
    }


    public int lengthOfLongestSubstring_2(String s) {
        // 滑动窗口。
        // 从字符集的角度考虑问题。
        // 哈希集合，记录每个字符是否出现过
        Set<Character> set = new HashSet<>();
        int maxL = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            // 只要发现 set 里面有重复，那么就一直让左指针去移动。
            while (left < right && set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxL = Math.max(maxL, right - left + 1);
        }
        return maxL;
    }
}
