package Java.hot_100;

public class _322 {

    public int coinChange(int[] coins, int amount) {
        // 动态规划，有点类似于完全平方数。
        int[] dp = new int[amount + 1];
        // dp[i] 的含义是，凑成 i 所需的最少的硬币个数。
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = -1;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1) dp[i] = dp[i - coins[j]] + 1;
                    else dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount];
    }
}
