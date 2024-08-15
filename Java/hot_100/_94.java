package Java.hot_100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _94 {
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        // 递归解法。
        if (root == null) {
            return List.of();
            // List.of() 返回一个空列表。
        }
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }

    public List<Integer> inorderTraversal_2(TreeNode root) {
        // 迭代解法。
        List<Integer> res = new ArrayList<>();
        // Java 里面的栈 -> Stack
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                // 一直往左走。
                stack.push(root);
                root = root.left;
            }
            TreeNode mid = stack.pop();
            res.add(mid.val);
            root = mid.right;
        }
        return res;
    }
}
