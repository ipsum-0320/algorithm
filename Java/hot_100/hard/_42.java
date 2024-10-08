package Java.hot_100.hard;

public class _42 {

    public int trap(int[] height) {
        int[] prefix = new int[height.length];
        prefix[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }
        int[] suffix = new int[height.length];
        suffix[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            // 注意只考虑 [1, height.length - 2]，第 0 个和第 height.length - 1 个不可能存水。
            int atomic = Math.min(prefix[i - 1], suffix[i + 1]) - height[i];
            // 如果 atomic < 0，说明这个位置不可能存水，直接跳过。
            res += Math.max(0, atomic);
        }

        return res;
    }

    public int trap_2(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;

        int leftMax = height[left], rightMax = height[right];
        // 这里的 leftMax 和 rightMax 是用来记录左右两边的最大值的。

        while (left + 1 < right) {
            // 每次总能确定左右两边其中一个桶能容纳多少水。
            int leftHeight = height[left], rightHeight = height[right];

            leftMax = Math.max(leftHeight, leftMax);
            rightMax = Math.max(rightHeight, rightMax);
            // 更新左右两边的最大值，这里其实就是前缀最大和后缀最大。

            if (leftMax < rightMax) {
                res += Math.max(0, leftMax - height[left + 1]);
                left++;
            } else {
                res += Math.max(0, rightMax - height[right - 1]);
                right--;
            }
        }

        return res;
    }

}
