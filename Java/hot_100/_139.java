package Java.hot_100;

import java.util.List;

public class _139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        // dp[i] 表示 s 的前 i 个字符能不能被拼接。
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] = dp[j] && wordDict.contains(s.substring(j, i));
                if (dp[i]) break;
            }
        }
        return dp[s.length()];
    }

}
