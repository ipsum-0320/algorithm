package Java.hot_100;

public class _45 {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i - j) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    public int jump_2(int[] nums) {
        int time = 0;
        int max = 0, right = 0;
        // right 表示右边界。
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            // 寻找能到的最远的地方。
            if (i == right) {
                // 到边界了。
                time++;
                // 到边界就得再跳一次。
                right = max;
            }
        }
        return time;
    }
}
