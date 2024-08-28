package Java.hot_100;

import java.util.List;
import java.util.Stack;

public class _739 {

    public int[] dailyTemperatures(int[] temperatures) {
        // 使用栈，一次压入两个元素 (value, index)。
        Stack<List<Integer>> stack = new Stack<>();
        int [] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty()) stack.push(List.of(temperatures[i], i));
            else {
                if (temperatures[i] <= stack.peek().get(0)) {
                    stack.push(List.of(temperatures[i], i));
                } else {
                    while (!stack.isEmpty() && temperatures[i] > stack.peek().get(0)) {
                        List<Integer> list = stack.pop();
                        res[list.get(1)] = i - list.get(1);
                    }
                    stack.push(List.of(temperatures[i], i));
                }
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            res[list.get(1)] = 0;
        }
        return res;
    }

}
