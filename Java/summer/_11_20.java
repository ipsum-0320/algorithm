package Java.summer;

import java.util.PriorityQueue;

public class _11_20 {
    // 239-48
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 滑动窗口 + 最大堆，注意堆要同时存储值和下标。
        // 存储下标是为了最大值的下标为 [i, i + k] 之间。
        PriorityQueue<Integer[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 最大堆。
        for (int i = 0; i < k; i++) {
            heap.add(new Integer[]{nums[i], i});
        }

        int[] res = new int[nums.length - k + 1];
        for (int i = k; i < nums.length; i++) {
            res[i - k] = heap.peek()[0];

            heap.add(new Integer[]{nums[i], i});
            // 把最后一个元素加进去，记得补偿。
            while (heap.peek()[1] < i - k + 1) {
                heap.poll();
            }
        }
        res[nums.length - k] = heap.peek()[0];
        // 补偿。

        return res;
    }

    // 76

}
