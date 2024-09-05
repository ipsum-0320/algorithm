package Java.hot_100;

public class _55 {
    public boolean canJump(int[] nums) {
        // 使用贪心算法，实时保存能够跳跃的最远距离。
        if (nums.length == 1) return true;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max >= i) {
                max = Math.max(max, i + nums[i]);
                if (max >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean canJump_2(int[] nums) {
        // 动态规划，dp[i] 定义为能否跳跃到第 i 个元素上。
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[nums.length - 1];
    }
}
