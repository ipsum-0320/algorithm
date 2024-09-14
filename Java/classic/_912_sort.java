package Java.classic;

public class _912_sort {
    public int[] sortArray(int[] nums) {
        // 快速排序。
        // 首先进行乱序。
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int random = (int) (Math.random() * (len - i)) + i;
            int temp = nums[i];
            nums[i] = nums[random];
            nums[random] = temp;
        }

        helper(nums, 0, nums.length - 1);
        return nums;
    }

    public void helper(int[] nums, int left, int right) {
        if (left >= right) return;
        int l = left, r = right;
        int temp = nums[left];
        while (l < r) {
            while (l < r && nums[r] >= temp) r--;
            // 先 r-- 是为了保证最后的 nums[l] 是比 temp 小的。
            while (l < r && nums[l] <= temp) l++;
            if (l < r) {
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
            }
        }
        nums[left] = nums[l];
        nums[l] = temp;

        helper(nums, left, l - 1);
        helper(nums, l + 1, right);
    }


    public int[] sortArray_2(int[] nums) {
        // 归并排序。
        return helper_2(nums, 0, nums.length - 1);
    }

    public int[] helper_2(int[] nums, int left, int right) {
        if (left >= right) return new int[]{nums[left]};
        // 定义数组并初始化。

        int mid = left + (right - left) / 2;
        int[] nums1 = helper_2(nums, left, mid);
        int[] nums2 = helper_2(nums, mid + 1, right);

        int[] res = new int[nums1.length + nums2.length];
        int p1 = 0, p2 = 0, cur = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] <= nums2[p2]) {
                res[cur] = nums1[p1];
                p1++;
            } else {
                res[cur] = nums2[p2];
                p2++;
            }
            cur++;
        }
        while (p1 < nums1.length) res[cur++] = nums1[p1++];
        while (p2 < nums2.length) res[cur++] = nums2[p2++];
        return res;
    }
}
