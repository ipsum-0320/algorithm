package Java.hot_100;

public class _64 {

    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[2][n];
        dp[0][0] = grid[0][0];

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            dp[i & 1][0] = dp[(i - 1) & 1][0] + grid[i][0];
            // 滚动数组，记得更新 dp[i][0]。
            for (int j = 1; j < n; j++) {
                dp[i & 1][j] = Math.min(dp[(i - 1) & 1][j], dp[i & 1][j - 1]) + grid[i][j];
            }
        }
        return dp[(m - 1) & 1][n - 1];
    }

}
