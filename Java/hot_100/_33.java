package Java.hot_100;

public class _33 {

    public int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int boundary = helper(nums);
        int left, right;

        if (boundary != nums.length - 1) {
            if (target >= nums[0]) {
                // 在左半部分查找。
                left = 0;
                right = boundary;
            } else {
                // 在右半部分查找。
                left = boundary + 1;
                // 这里可能会造成数组越界。
                right = nums.length - 1;
            }
        } else {
            left = 0;
            right = nums.length - 1;
        }

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid;
            else right = mid;
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    public int search_2(int[] nums, int target) {
        // 丢弃不可能的一侧。
        int left = 0, right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > nums[0]) {
                // 在前半部分。
                if (target >= nums[0] && target < nums[mid]) right = mid;
                else left = mid;
            } else {
                // 在后半部分。
                if (target > nums[mid] && target < nums[0]) left = mid;
                else right = mid;
            }
        }

        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }


    public int helper(int[] nums) {
        // 二分查找，用于寻找分界点。
        int left = 0, right = nums.length - 1;
        int target = nums[0];
        // 寻找最后一个比 target 大的元素。
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) left = mid;
            else if (nums[mid] > target) left = mid;
            // 这里是找最后一个比 target 大的元素，所以要找更大的，移动 left。
            else right = mid;
        }
        if (nums[right] >= target) return right;
        return left;
    }

}
