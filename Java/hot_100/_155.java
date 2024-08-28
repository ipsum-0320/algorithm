package Java.hot_100;

import java.util.List;
import java.util.Stack;

public class _155 {
}

class MinStack {
    Stack<Integer> stack = new Stack<>();
    // 起到一个提供默认值的作用。
    Stack<Integer> minStack = new Stack<>();

    public MinStack() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        int min = minStack.peek();
        minStack.push(Math.min(val, min));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

class MinStack_2 {
    Stack<List<Integer>> stack = new Stack<>();
    // 元组是 (x, min)。

    public MinStack_2() {
        stack.push(List.of(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public void push(int val) {
        int min = Math.min(val, getMin());
        stack.push(List.of(val, min));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().get(0);
    }

    public int getMin() {
         return stack.peek().get(1);
    }
}

