package Java.hot_100;

public class _238 {
    public int[] productExceptSelf(int[] nums) {
        // 除自身以外的乘积，搞懂 left 和 right 的定义很重要。
        int[] left = new int[nums.length];
        // left 表示连同当前元素的前缀乘积。
        int[] right = new int[nums.length];
        // right 表示连同当前元素的后缀乘积。
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) left[i] = nums[i];
            else left[i] = left[i - 1] * nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) right[i] = nums[i];
            else right[i] = right[i + 1] * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) nums[i] = right[i + 1];
            else if (i == nums.length - 1) nums[i] = left[i - 1];
            else nums[i] = left[i - 1] * right[i + 1];
        }
        return nums;
    }

    public int[] productExceptSelf_2(int[] nums) {
        // 除自身以外的乘积，搞懂 left 和 right 的定义很重要。
        int[] res = new int[nums.length];
        // 一开始 res[i] 表示 i 左边元素的乘积。
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) res[i] = 1;
            else res[i] = res[i - 1] * nums[i - 1];
        }
        // 后面要对右边元素进行乘积。
        int R = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 res[i]，右边的乘积为 R。
            res[i] = res[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上。
            R *= nums[i];
        }
        return res;
    }
}
