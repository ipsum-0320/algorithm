package Java.hot_100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _98 {
    Map<TreeNode, Long> maxMap = new HashMap<>();
    Map<TreeNode, Long> minMap = new HashMap<>();

    public boolean isValidBST(TreeNode root) {
        maxMap.put(null, Long.MIN_VALUE);
        minMap.put(null, Long.MAX_VALUE);
        maxVal(root);
        minVal(root);
        return helper(root);
    }

    public boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = helper(root.left);
        boolean right = helper(root.right);
        return root.val > maxMap.get(root.left) && root.val < minMap.get(root.right) && left && right;
    }


     public long maxVal(TreeNode root) {
         if (root == null) {
             return Long.MIN_VALUE;
         }
         long left = maxVal(root.left);
         long right = maxVal(root.right);
         long max = Math.max(root.val, Math.max(left, right));
         maxMap.put(root, max);
         return max;
     }

    public long minVal(TreeNode root) {
        if (root == null) {
            return Long.MAX_VALUE;
        }
        long left = minVal(root.left);
        long right = minVal(root.right);
        long min =  Math.min(root.val, Math.min(left, right));
        minMap.put(root, min);
        return min;
    }

    public boolean isValidBST_2(TreeNode root) {
        // 使用上下限递归。
        return helper_2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean helper_2(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        return helper_2(root.left, lower, root.val) && helper_2(root.right, root.val, upper);
    }




    public boolean isValidBST_3(TreeNode root) {
        // 使用中序遍历。
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            // 这里必须要有 root != null 的判断。
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;
    }
}
