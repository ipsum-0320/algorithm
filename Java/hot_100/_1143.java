package Java.hot_100;

public class _1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        // 动态规划。
        int[][] dp = new int[2][text2.length() + 1];
        for (int i = 0; i < 2; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= text2.length(); i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                int shortCommon = Math.max(dp[(i - 1) & 1][j], dp[i & 1][j - 1]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i & 1][j] = Math.max(shortCommon, dp[(i - 1) & 1][j - 1] + 1);
                } else {
                    dp[i & 1][j] = shortCommon;
                }
            }
        }
        return dp[text1.length() & 1][text2.length()];
    }

}
