package Java.hot_100;

public class _70 {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // 记录最后一步即可。

        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] += dp[i - 1];
            if (i - 2 >= 0) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

}
