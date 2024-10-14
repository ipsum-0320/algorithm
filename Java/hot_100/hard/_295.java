package Java.hot_100.hard;

import java.util.PriorityQueue;

public class _295 {
}

class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    int count;

    public MedianFinder() {
        this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
        this.minHeap = new PriorityQueue<>((a, b) -> a - b);
        this.count = 0;
    }

    public void addNum(int num) {
        count++;
        // 如果 count 是偶数，那么就往最小堆中添加。
        if (count % 2 == 0) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            // 如果 count 是奇数，那么就往最大堆中添加。
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (count % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */