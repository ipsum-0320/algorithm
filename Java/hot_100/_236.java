package Java.hot_100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _236 {
    Map<TreeNode, TreeNode> map = new HashMap<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归，存储每个节点的父节点。
        helper(root);
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = map.get(p);
        }
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = map.get(q);
        }
        return null;
    }

    public void helper(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null)
            map.put(root.left, root);
        if (root.right != null)
            map.put(root.right, root);
        helper(root.left);
        helper(root.right);
    }
}
