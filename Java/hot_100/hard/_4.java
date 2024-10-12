package Java.hot_100.hard;

public class _4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 转化为一道递归的题目即可。
        int all = nums1.length + nums2.length;
        if (all % 2 == 0) {
            // 长度是偶数。
            double left = dfs(nums1, nums2, 0, 0, all / 2);
            double right = dfs(nums1, nums2, 0, 0, all / 2 + 1);
            return (left + right) / 2;
        }
        // 长度是奇数。
        return dfs(nums1, nums2, 0, 0, all / 2 + 1);
    }

    public double dfs(int[] nums1, int[] nums2, int left, int right, int k) {
        // left 表示 nums1 的开始指针，right 表示 nums2 的开始指针，k 表示寻找第 k 大的数字。
        // 为了减少讨论情况，我们保证 nums1 的长度小于 nums2 的长度。
        if (nums1.length - left > nums2.length - right) {
            return dfs(nums2, nums1, right, left, k);
        }
        // nums1 为空，直接返回 nums2 的第 k 个元素。
        // 只可能把 nums1 丢空，nums2 不会丢空，因为 nums2.length > nums1.length，中位数不会存在于 nums2 丢空后的 nums1 中。
        if (left >= nums1.length) {
            return nums2[right + k - 1];
        }
        // k = 1，返回两个数组的最小值。
        if (k == 1) {
            return Math.min(nums1[left], nums2[right]);
        }

        int i = Math.min(nums1.length, left + k / 2) - 1;
        // 找到 nums1 的第 k/2 个元素。
        int j = right + k / 2 - 1;
        // 找到 nums2 的第 k/2 个元素。

        if (nums1[i] > nums2[j]) {
            // 丢掉 nums2 的前 j 个元素。
            return dfs(nums1, nums2, left, j + 1, k - (j - right + 1));
            // j - right + 1 就是被丢弃的元素个数。
        }
        // 丢掉 nums1 的前 i 个元素。
        return dfs(nums1, nums2, i + 1, right, k - (i - left + 1));
    }
}
