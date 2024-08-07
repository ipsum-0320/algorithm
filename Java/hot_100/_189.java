package Java.hot_100;

public class _189 {

    public void rotate(int[] nums, int k) {
        // 第一种解法：使用额外的数组。
        // 首先要取余数，防止循环。
        int finalK = k % nums.length;
        int[] res = new int[nums.length];
        // 开辟一个新数组复制 nums。
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }
        // 旋转后的数组的前 finalK 个元素是原数组的后 nums.length - finalK 个元素。
        int startRotate = nums.length - finalK;
        int start = 0;
        for (int i = startRotate; i < nums.length; i++) {
            nums[start++] = res[i];
        }
        for (int i = 0; i < startRotate; i++) {
            nums[start++] = res[i];
        }
    }

    public void rotate_2(int[] nums, int k) {
        // 使用数组反转的方法。
        reverse(nums, 0 , nums.length - 1);
        int finalK = k % nums.length;
        reverse(nums, 0, finalK - 1);
        reverse(nums, finalK, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
