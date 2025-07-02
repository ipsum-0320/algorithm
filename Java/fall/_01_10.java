package Java.fall;

import java.util.HashMap;
import java.util.Map;

public class _01_10 {
    // 1 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 只会存在一个有效答案，这已经排除了出现重复答案的情况。
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
