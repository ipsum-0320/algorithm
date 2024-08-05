package Java.hot_100;

public class _53 {
    public int maxSubArray(int[] nums) {
        int preSumMin = 0; // 过往前缀和的最小值。
        int preSum = 0; // 当前的前缀和。
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            preSum += num;
            res = Math.max(res, preSum - preSumMin);
            preSumMin = Math.min(preSumMin, preSum);
        }
        return res;
    }

    public int maxSubArray_2(int[] nums) {
        int[] dp = new int[nums.length];
        // dp 表示以 nums[i] 结尾的最大子数组和。
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;
    }
}
