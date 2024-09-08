package Java.hot_100;

import java.util.ArrayList;
import java.util.List;

public class _300 {

    public int lengthOfLIS(int[] nums) {
        // 动态规划解法。
        int[] dp = new int[nums.length];
        // dp[i] 的含义是以 nums[i] 结尾的最长递增子序列。
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    res = Math.max(res, dp[i]);
                }
            }
        }
        // 题目中问的是最长递增子序列，不一定以最后一个 nums[i] 结尾。
        return res;
    }

    public int lengthOfLIS_2(int[] nums) {
        // 贪心 + 二分查找。
        List<Integer> helper = new ArrayList<>();
        // helper[i] 表示长度为 i + 1 的递增子序列的最大值。
        helper.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int target = nums[i];
            if (target > helper.get(helper.size() - 1)) {
                helper.add(nums[i]);
                continue;
            }
            if (target < helper.get(helper.size() - 1)) {
                // 需要进行替换。
                // 根据数组 helper[i] 的定义，一定可以找到一个可替换的选项。
                // ---
                // 任务转化为寻找比第一个大于等于 target 的元素的 index。
                // 不能寻找第一个大于且不等于 target 的元素，否则会从 helper = [4, 10]，target = 4 变成 helper = [4, 4]，不递增了。
                int left = 0, right = helper.size() - 1;
                while (left + 1 < right) {
                    int mid = left + (right - left) / 2;
                    if (helper.get(mid) >= target) right = mid;
                    else left = mid;
                }
                if (helper.get(left) >= target) {
                    helper.set(left, target);
                } else {
                    helper.set(right, target);
                }
            }
        }
        return helper.size();
    }

}
