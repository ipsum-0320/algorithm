package Java.hot_100;

import java.util.HashMap;
import java.util.Map;

public class _560 {
    public int subarraySum(int[] nums, int k) {
        // 可以通过直接遍历的方式来做。
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                // nums[end] 有可能是负数。
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum_2(int[] nums, int k) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        // 构建前缀和数组，preSum[i] 表示前 i 个数字的和。
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int left = 0; left <= len; left++) {
            for (int right = left + 1; right <= len; right++) {
                // 区间和 [left..right]，注意下标偏移，区间长度不能为 0。
                if (preSum[right] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum_3(int[] nums, int k) {
        // 前缀和 + 哈希表优化。
        // key：前缀和，value：key 对应的前缀和的个数。
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1。
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;
            // 先获得前缀和为 preSum - k 的个数，加到计数变量里。
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
