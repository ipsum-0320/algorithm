package Java.hot_100;

import java.util.HashSet;
import java.util.Set;

public class _128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                // 对于最小的才开始计算。
                int temp = 0;
                while (set.contains(num)) {
                    temp++;
                    num++;
                }
                longest = Math.max(longest, temp);
            }
        }
        return longest;
    }
}
