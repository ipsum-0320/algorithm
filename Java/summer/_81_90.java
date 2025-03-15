package Java.summer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class _81_90 {
    // 70-32
    // 70
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 118
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1) return List.of(List.of(1));
        if (numRows == 2) return List.of(List.of(1), List.of(1, 1));
        List<List<Integer>> list = new ArrayList<>();
        list.add(List.of(1));
        list.add(List.of(1, 1));
        for (int i = 2; i < numRows; i++) {
            List<Integer> inner = new ArrayList<>();
            inner.add(1);
            for (int j = 1; j < i; j++) {
                inner.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
            }
            inner.add(1);
            list.add(inner);
        }
        return list;
    }

    // 198
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[nums.length - 1];
    }

    // 279
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[1] = 1;
        dp[0] = 0;
        // dp[0] 不考虑。
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    // 322
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = -1;
            for (int j = 0; j < coins.length; j++) {
                if (i < coins[j]) continue;
                if (dp[i - coins[j]] == -1) continue;
                if (dp[i] == -1) dp[i] = dp[i - coins[j]] + 1;
                else dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount];
    }

    // 139
    public boolean wordBreak(String s, List<String> wordDict) {
        // 动规。
        boolean[] dp = new boolean[s.length() + 1];
        // dp[i] 表示前 i 个字符能不能被组成。
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = false;
            for (int j = 0; j < wordDict.size(); j++) {
                if (i - wordDict.get(j).length() >= 0) {
                    String sub = s.substring(i - wordDict.get(j).length(), i);
                    dp[i] = dp[i - wordDict.get(j).length()] && wordDict.contains(sub);
                    if (dp[i]) break;
                    // 一旦找到了，就需要跳出循环了。
                }
            }
        }
        return dp[s.length()];
    }

    // 300
    public int lengthOfLIS(int[] nums) {
        // O(N^2)
        int[] dp = new int[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (dp[i] > dp[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
    // 152
    // 分正负讨论即可。

    // 416
    public boolean canPartition(int[] nums) {
        // 用背包型动态规划。
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;

        boolean [][] dp = new boolean[2][target + 1];
        // dp[i][j] 表示 [0, i] 的数字能不能组成 j。
        // 这里 boolean[2][target + 1] 用的是滚动数组。
        // 背包问题的核心 => 不放第 i 个东西 || 放第 i 个东西。

        // 初始化：不选第一个元素时和为 0，选第一个元素时和为 nums[0]（如果不超过target）
        dp[0][0] = true; // 不选第一个元素
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j] || dp[(i - 1) % 2][j - nums[i]];
                } else {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                }
            }
        }
        return dp[nums.length % 2][target];
    }

    // 32
    public int longestValidParentheses(String s) {
        // 用栈来保证是最长有效括号。
        // 其实分情况讨论的话，一共就这几种：((  )(  ()  ))
        // 有效的其实就是 ((  ()。
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        // 放入一个基准位置。
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                // 把 ( 出栈。
                if (stack.isEmpty()) {
                    // 如果 stack 为空，说明需要更新基准位置了。
                    stack.push(i);
                    continue;
                }
                max = Math.max(max, i - stack.peek());
                // 最后的计算应该是 当前位置 - 基准位置（peek）。
            }
        }
        return max;
    }
}

// 饿了么。
class Solution {
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        // 先去判断能不能到，贪心!
        long cur = 0;
        long fuel = startFuel;
        for (int i = 0; i < stations.length; i++) {
            if (stations[i][0] - cur <= fuel) {
                fuel -= (stations[i][0] - cur);
                fuel += stations[i][1];
                cur = stations[i][0];
            } else return -1;
        }
        if (fuel < target - cur) return -1;

        cur = 0;
        fuel = startFuel;
        int res = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        // 利用最大堆存储所有的油。
        for (int i = 0; i < stations.length; ) {
            if (stations[i][0] - cur <= fuel) {
                // 能加到油。
                queue.add(stations[i][1]);
                fuel -= (stations[i][0] - cur);
                cur = stations[i][0];
                i++;
            } else {
                int add = queue.poll();
                fuel += add;
                res++;
            }
        }
        while (fuel < target - cur) {
            // 要想走完全程，还得加油。
            int add = queue.poll();
            fuel += add;
            res++;
        }
        return res;
    }

}