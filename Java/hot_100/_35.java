package Java.hot_100;

public class _35 {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) right = mid;
            // 如果找到了 target，因为要找最后一个比 target 小的元素，所以继续向左搜索。
            else if (nums[mid] < target) left = mid;
            else right = mid;
        }
        if (nums[right] < target) return right + 1;
        else if (nums[left] < target) return left + 1;
        else return left;
        // 如果所有的都比 target 大，那么 target 应该插入到第一个元素的前面。
    }

}
