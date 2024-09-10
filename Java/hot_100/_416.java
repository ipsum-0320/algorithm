package Java.hot_100;

public class _416 {

    public boolean canPartition(int[] nums) {
        // 转化成背包型动态规划。
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;

        boolean [][] dp = new boolean[nums.length][target + 1];
        // dp[i][j] 表示 [0, i] 的数字能不能组成 j。
        // 背包问题的初始条件。
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= target) dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) // 处理背包问题的边界情况。
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[nums.length - 1][target];
    }

    public boolean canPartition_2(int[] nums) {
        // 转化成背包型动态规划。
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;

        boolean [][] dp = new boolean[2][target + 1];
        // dp[i][j] 表示 [0, i] 的数字能不能组成 j。
        // 背包问题的初始条件。
        for (int i = 0; i < 2; i++) {
            // 虽然只更新了前两行，但是后面每一行的首个元素均是 true，所以没关系。
            dp[i][0] = true;
        }
        if (nums[0] <= target) dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) // 处理背包问题的边界情况。
                    dp[i & 1][j] = dp[(i - 1) & 1][j] || dp[(i - 1) & 1][j - nums[i]];
                else dp[i & 1][j] = dp[(i - 1) & 1][j];
            }
        }
        return dp[(nums.length - 1) & 1][target];
    }


}
