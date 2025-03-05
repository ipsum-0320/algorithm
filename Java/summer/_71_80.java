package Java.summer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

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

    }

}
