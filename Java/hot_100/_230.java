package Java.hot_100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _230 {
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历，寻找第 k 小的元素。
        int cur = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            cur++;
            if (cur == k) {
                return root.val;
            }
            root = root.right;
        }
        return -1;
    }

    Map<TreeNode, Integer> map = new HashMap<>();

    public int kthSmallest_2(TreeNode root, int k) {
        helper(root);
        map.put(null, 0);
        while (root != null) {
            int num = map.get(root.left);
            if (num == k - 1) {
                return root.val;
            } else if (num < k - 1) {
                root = root.right;
                k -= (num + 1);
                // 这里记得更新 k 值，因为父节点的左子树及父节点本身已经计算不到且被排除了。
            } else {
                root = root.left;
            }
        }
        return -1;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int sum = left + right + 1;
        map.put(root, sum);
        return sum;
    }
}