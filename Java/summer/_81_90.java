package Java.summer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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