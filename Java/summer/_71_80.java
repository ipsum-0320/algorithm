package Java.summer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

public class _71_80 {
    // 394-763
    // 394
    public String decodeString(String s) {
        // 直接使用 Stack + for 循环解决。
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                // 只要不是 ] 就入栈，否则进行逻辑处理。
                ArrayList<String> sub = new ArrayList<>();
                while (!Objects.equals(stack.peek(), "[")) {
                    sub.add(stack.pop());
                    // 找出 [] 间所有的字符。
                }
                stack.pop();
                // 弹出 [
                Collections.reverse(sub);
                // 翻转，因为 stack pop 是相反的顺序。
                String str = String.join("", sub);
                sub.clear();
                while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
                    sub.add(stack.pop());
                }
                // 获取数字。
                Collections.reverse(sub);
                int num = Integer.parseInt(String.join("", sub));
                sub.clear();
                for (int j = 0; j < num; j++) {
                    sub.add(str);
                }
                // 将多倍的 str 连接起来。
                String finalStr = String.join("", sub);
                stack.push(finalStr);
                // 最后的 str 需要入栈。
            } else {
                stack.push(String.valueOf(s.charAt(i)));
                // String.valueOf 将 char 转化为 String。
            }
        }
        return String.join("", stack);
        // 记得是多个 String 的串联。
    }

    // 739-每日温度，直接用 Stack。
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<List<Integer>> stack = new Stack<>();
        // 其中 stack 存储了 [value, index]。
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() &&
                    temperatures[i] > stack.peek().get(0)) {
                // 核心就是保证 stack 是一个单调递减的栈。
                List<Integer> unit = stack.pop();
                res[unit.get(1)] = i - unit.get(1);
            }
            stack.push(List.of(temperatures[i], i));
        }
        while (!stack.isEmpty()) {
            List<Integer> unit = stack.pop();
            res[unit.get(1)] = 0;
        }
        return res;
    }

    // 84
    public int largestRectangleArea(int[] heights) {
        // 使用双指针去做。
        // 关键是弄清楚变量含义。
        int[] left = new int[heights.length];
        // left[i] 表示左边第一个 < heights[i] 的元素。
        int[] right = new int[heights.length];
        // right[i] 表示右边第一个 < heights[i] 的元素。

        left[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            int l = i - 1;
            while (l != -1 && heights[l] >= heights[i]) {
                l = left[l];
                // 这里是 left[l] 表示左边第一个小于 heights[l] 的元素。
            }
            left[i] = l;
        }

        right[heights.length - 1] = heights.length;
        for (int i = heights.length - 2; i >= 0; i--) {
            int r = i + 1;
            while (r != heights.length && heights[r] >= heights[i]) {
                r = right[r];
            }
            right[i] = r;
        }

        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }

    // 215
    public int findKthLargest(int[] nums, int k) {
        // 直接使用堆来完成。
        // 核心代码就是堆化代码。
        buildHeap(nums);
        int size = nums.length;
        for (int i = 1; i < k; i++) {
            nums[0] = nums[size - 1];
            size--;
            maxHeapify(nums, 0, size);
        }
        return nums[0];
    }
    public void buildHeap(int[] nums) {
        // 对 nums 做堆化，堆化应该是子弟向上的，而不是自顶向下（防止回溯）。
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            maxHeapify(nums, i, nums.length);
        }

    }
    public void maxHeapify(int[] nums, int i, int size) {
        // nums 就是堆容器，i 则是堆的根节点，size 则是堆的容器。
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;
        // 最大堆。
        if (left < size && nums[left] > nums[largest]) {
            // 必须是两个 if，选出最大的来，不能加 else。
            largest = left;
        }
        if (right < size && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != i) {
            // 交换两者的取值。
            int tmp = nums[largest];
            nums[largest] = nums[i];
            nums[i] = tmp;
            // largest 上的数值做了更新，对子树做堆化。
            maxHeapify(nums, largest, size);
        }
    }

    // 347
    public int[] topKFrequent(int[] nums, int k) {
        // 前 k 个高频元素。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>((a, b) -> b.get(1) - a.get(1));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.add(List.of(entry.getKey(), entry.getValue()));
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll().get(0);
        }
        return res;
    }

    // 295
    class MedianFinder {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int count = 0;

        public MedianFinder() {
            // 最大堆（存储较小的那一段）和最小堆（存储较大的那一段）相配合。
            // 中位数会暴露在最大堆和最小堆的堆顶。
        }

        public void addNum(int num) {
            if (maxHeap.isEmpty()) maxHeap.add(num);
            else if (minHeap.isEmpty()) {
                if (num >= maxHeap.peek()) minHeap.add(num);
                else {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(num);
                }
            } else {
                if (this.count % 2 == 0) {
                    // 想办法将 maxHeap 多一个。
                    if (num <= minHeap.peek()) maxHeap.add(num);
                    else {
                        maxHeap.add(minHeap.poll());
                        minHeap.add(num);
                    }
                } else {
                    // 如果是奇数，想办法让 minHeap 多一个。
                    if (num >= maxHeap.peek()) minHeap.add(num);
                    else {
                        minHeap.add(maxHeap.poll());
                        maxHeap.add(num);
                    }
                }
            }
            this.count++;
        }

        public double findMedian() {
            // 定义如果是奇数，中位数放在最大堆，如果是偶数，就二者相加 / 2。
            if (this.count % 2 == 1) return this.maxHeap.peek();
            return (this.maxHeap.peek() + this.minHeap.peek()) / 2.0;
        }
    }

    // 121
    public int maxProfit(int[] prices) {
        int res = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return res;
    }

    // 55
    public boolean canJump(int[] nums) {
        // 直接用 dp 来做，时间复杂度是 O(N^2)。
        boolean[] dp = new boolean[nums.length];
        // dp[i] 表示能否到达下标 i。
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            while (j >= 0) {
                int step = nums[j];
                dp[i] = dp[j] && (j + step  >= i);
                if (dp[i]) break;
                j--;
            }
        }
        return dp[nums.length - 1];
    }

    // 55-贪心。
    public boolean canJump_2(int[] nums) {
        // 使用贪心算法，实时保存**能够跳跃的最远距离**。
        if (nums.length == 1) return true;
        int max = nums[0];
        // 关键就是保存能跳跃的最远距离。
        for (int i = 1; i < nums.length; i++) {
            if (max >= i) {
                max = Math.max(max, i + nums[i]);
                if (max >= nums.length - 1) return true;
            }
        }
        return false;
    }

    // 45
    public int jump(int[] nums) {
        // dp，题意会保证肯定可以跳完。
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i)
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[nums.length - 1];
    }

    public int jump_2(int[] nums) {
        // 贪心-维护最远能到的地方。
        int res = 0;
        int max = 0, right = 0;
        // right 表示右边界，max 表示边界内的 i 最远能去哪里。
        for (int i = 0; i < nums.length - 1; i++) {
            // 最后一步 nums.length 不算（已经到了 nums 的末尾），因此要 -1。
            max = Math.max(max, i + nums[i]);
            if (i == right) {
                res++;
                right = max;
                // 更新边界。
            }
        }
        return res;
    }

    // 763
    public List<Integer> partitionLabels(String s) {
        // 需要有一个 map list 记录每种字符最靠后的 index。
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        int last = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            last = Math.max(last, map.get(s.charAt(i)));
            length++;
            if (i == last) {
                res.add(length);
                length = 0;
            }
        }
        return res;
    }




}
