package Java.hot_100;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _215 {

    public int findKthLargest(int[] nums, int k) {
        Comparator<Integer> comparator = (o1, o2) -> o2 - o1;

        PriorityQueue<Integer> queue = new PriorityQueue<>(comparator);
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        for (int i = 0; i < k; i++) {
            queue.remove();
        }
        return queue.peek();
    }


    public int findKthLargest_2(int[] nums, int k) {
        // 新建堆。
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            nums[0] = nums[i];
            --heapSize;
            maxHeapify(nums, 0, heapSize);
            // 重新堆化，这里的参数 i 就是根节点 root，heapSize 是堆大小。
        }
        return nums[0];
    }


    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = (heapSize - 1) / 2; i >= 0; --i) {
            // 从最后一个子堆的根节点开始进行建堆。
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            maxHeapify(a, largest, heapSize);
        }
    }
}