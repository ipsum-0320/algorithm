package Java.hot_100;

public class _31 {

    public void nextPermutation(int[] nums) {
        // 下一个排列。
        /*
         * (1) 我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
         * (2) 同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。
         * (3) 这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
         * */
        if (nums.length == 1) return;
        int index;
        // 这里的 index 是**从后往前**的第一个 (小，大) 的那个 "大" 的下标。
        for (index = nums.length - 1; index - 1 >= 0; index--) {
            if (nums[index - 1] < nums[index]) break;
        }
        index--;
        // 自减，此时 index 指向了那个 "小" 的下标。
        // 现在，从 index 开始，序列分布为 _-_，即 index 到 index + 1 上升，之后全部是下降的。

        if (index != -1) {
            // 纯递减序列已经没办法变得更大了，此时 index 为 -1，例如序列 [4, 3, 2, 1]。
            for (int i = nums.length - 1; i > index; i--) {
                // 从 nums.length - 1 到 index + 1 是递增的，找到第一个比 index 大的。
                // 交换之后，依旧从 nums.length - 1 到 index + 1 是递增的
                if (nums[i] > nums[index]) {
                    int temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                    break;
                }
            }
        }

        // 翻转，reverse。
        for (int i = index + 1, j = nums.length - 1; i <= j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}
