package Java.hot_100.hard;

public class _41 {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 最终的答案一定位于 [1, n + 1] 中间。

        for (int i = 0; i < n; i++) {
            // 满足在指定范围内、并且没有放在正确的位置上，才交换，因为要将下标为 i 的元素
            // 放在下标为 nums[i] - 1 的位置上，因此需要确保 nums[i] != nums[nums[i] - 1]]。
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                // 交换 nums[i] 和 nums[nums[i] - 1] 的位置。
                // 为了保证交换过来的数也放在正确位置，因此继续循环。
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                // 找到第一个不满足条件的数，返回。
                return i + 1;
            }
        }

        return n + 1;
    }

    public void swap(int[] nums, int i, int j) {
        // 交换 i、j 位置的元素。
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
