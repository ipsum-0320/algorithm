package Java.hot_100;

public class _34 {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) right = mid;
                // 先找第一个 target。
            else if (nums[mid] < target) left = mid;
            else right = mid;
        }
        if (nums[left] == target) {
            res[0] = left;
        } else if (nums[right] == target) {
            res[0] = right;
        } else {
            return res;
        }

        left = 0;
        right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) left = mid;
                // 先找最后一个 target。
            else if (nums[mid] < target) left = mid;
            else right = mid;
        }

        if (nums[right] == target) {
            res[1] = right;
            return res;
        }
        res[1] = left;
        return res;
    }

}
