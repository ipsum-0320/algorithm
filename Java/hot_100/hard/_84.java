package Java.hot_100.hard;

public class _84 {

    public int largestRectangleArea(int[] heights) {
        // 双指针解法，有点类似于接雨水。
        // 本质上的思路：
        /*
         * 对数组中的每个元素，若假定以它为高，能够展开的宽度越宽，那么以它为高的矩形面积就越大。
         * 因此，思路就是找到每个元素左边第一个比它矮的矩形和右边第一个比它矮的矩形，在这中间的就是最大宽度。
         * 最后对每个元素遍历一遍找到最大值即可。
         * */
        int n = heights.length;
        int[] left = new int[n];
        // 左边第一个 < heights[i] 的位置。
        int[] right = new int[n];
        // 右边第一个 > heights[i] 的位置。

        left[0] = -1;
        for (int i = 1; i < n; i++) {
            int t = i - 1;
            while (t >= 0 && heights[t] >= heights[i]) {
                t = left[t];
                // 这一步不是 t-- 有效降低了时间复杂度。
                // 为什么可以用 left[t] 给 t 赋值？因为 heights[t] >= heights[i]，
                // 所以可以保证 left[t] 右边的值一定是大于 heights[i] 的。
            }
            left[i] = t;
        }

        right[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            int t = i + 1;
            while (t < n && heights[t] >= heights[i]) {
                t = right[t];
            }
            right[i] = t;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }

}
