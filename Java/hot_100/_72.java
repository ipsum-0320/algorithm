package Java.hot_100;

public class _72 {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[2][word2.length() + 1];
        // dp[i][j] 表示 word1.substr(0, i) => word2.substr(0, j) 的最短编辑距离。

        // 初始化
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            dp[i & 1][0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                dp[i & 1][j] = Integer.MAX_VALUE;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i & 1][j] = dp[(i - 1) & 1][j - 1];
                } else {
                    dp[i & 1][j] = Math.min(dp[(i - 1) & 1][j - 1] + 1, Math.min(dp[i & 1][j - 1] + 1, dp[(i - 1) & 1][j] + 1));
                    // 替换、添加、删除。
                }
            }
        }

        return dp[word1.length() & 1][word2.length()];
    }

}
