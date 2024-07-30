package Java;

import java.util.HashMap;
import java.util.Map;

public class _1 {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        // 1. Map 是一个泛型，记着添加泛型相关的。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                // map 判断是否包含 key 使用 containsKey。
                return new int[]{i, (int) map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
                // map 添加 k v，使用 put。
            }
        }
        return new int[0];
    }
}
