package Java.hot_100;

public class _5 {

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        // dp[i][j] 表示 s.substr(j, j + i) 是否是回文串。

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j + i <= s.length(); j++) {
                if (i == 0) dp[j][j + i] = true; // 长度为 0。
                else if (i == 1) dp[j][j + i] = true; // 长度为 1。
                else {
                    if (dp[j + 1][j + i - 1] && s.charAt(j) == s.charAt(j + i - 1))
                        dp[j][j + i] = true;
                }
            }
        }

        int left = 0, right = 0;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j + i <= s.length(); j++) {
                if (dp[j][j + i]) {
                    left = j;
                    right = j + i;
                }
            }
        }

        return s.substring(left, right);
    }


    public String longestPalindrome_2(String s) {
        // 不考虑长度为 0 的情况，将长度为 1 和长度为 2 的情况单列（这里的长度为 2 就涵盖了长度为 0）。
        boolean[][] dp = new boolean[s.length()][s.length()];
        // dp[i][j] 表示 s[i, j] 是否是回文串。

        for (int i = 0; i < s.length(); i++) {
            // i == 0 就是长度为 1，i == 1 就是长度为 2。
            for (int j = 0; j + i < s.length(); j++) {
                if (i == 0) dp[j][j + i] = true; // 长度为 1。
                else if (i == 1 && s.charAt(j) == s.charAt(j + i)) {
                    dp[j][j + i] = true; // 长度为 2。
                } else {
                    if (dp[j + 1][j + i - 1] && s.charAt(j) == s.charAt(j + i))
                        dp[j][j + i] = true;
                }
            }
        }

        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j + i < s.length(); j++) {
                if (dp[j][j + i]) {
                    left = j;
                    right = j + i + 1;
                }
            }
        }

        return s.substring(left, right);
    }
}
