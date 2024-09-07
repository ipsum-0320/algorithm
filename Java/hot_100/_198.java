package Java.hot_100;

public class _198 {

    public int rob(int[] nums) {
        // dp 中变量的含义很重要，dp[i] 表示前 i 间房屋，还是以第 i 间房屋结尾的房子？
        // 此处表示前 i 间房屋。
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[nums.length];
    }

    public int rob_2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        // dp[i] 表示以 i 结尾的房屋。
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

}
