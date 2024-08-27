package Java.hot_100;

public class _153 {

    public int findMin(int[] nums) {
        // 二分查找，最终 nums 的趋势为 / /。
        int left = 0, right = nums.length - 1;
        int target = nums[nums.length - 1];
        // 寻找小于 target 的第一个元素。

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) right = mid;
            else if (nums[mid] < target) right = mid;
            else left = mid;
        }

        if (nums[left] <= target) return nums[left];
        else return nums[right];
    }

}
