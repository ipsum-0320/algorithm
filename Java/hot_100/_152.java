package Java.hot_100;

public class _152 {

    public int maxProduct(int[] nums) {
        // 思路总体与最大子数组和类似，但是需要分正负。
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];
        // dp[i] 含义均为以 nums[i] 结尾的 maxProduct。

        maxDp[0] = nums[0];
        minDp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxDp[i] = Math.max(Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]), nums[i]);
            minDp[i] = Math.min(Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]), nums[i]);
        }

        // 注意最后的结果，maxProduct 并不是一定要以 nums[nums.length - 1] 结尾。
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, maxDp[i]);
        }
        return res;
    }

}
