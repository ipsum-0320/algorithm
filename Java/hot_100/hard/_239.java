package Java.hot_100.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class _239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0, right = k - 1;
        PriorityQueue<Integer[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 最大堆。
        for (int i = 0; i < k; i++) {
            heap.add(new Integer[]{nums[i], i});
        }
        int[] res = new int[nums.length - k + 1];
        while (right < nums.length) {
            int max = heap.peek()[0];
            res[left] = max;
            left++;
            right++;
            if (right == nums.length) {
                break;
            }
            while (!heap.isEmpty() && heap.peek()[1] < left) {
                heap.poll();
            }
            heap.add(new Integer[]{nums[right], right});
        }
        return res;
    }

    public int[] maxSlidingWindow_2(int[] nums, int k) {
        // 双端单调递减队列。
        Deque<Integer> queue = new LinkedList<>();
        // 双端队列是为了方便删除前面的元素。
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);
            // 存储的是下标，是为了方便删除前面的元素，通过下标比较确定是否要删除。
        }
        for (int i = k; i < nums.length; i++) {
            res[i - k] = nums[queue.peekFirst()];
            if (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.removeFirst();
            }
            // 下标的单调递增，值的单调递减。
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }
        res[nums.length - k] = nums[queue.peekFirst()];
        return res;
    }
}
