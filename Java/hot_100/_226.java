package Java.hot_100;

public class _226 {
    public TreeNode invertTree(TreeNode root) {
        // 递归解法。
        if (root == null) {
            return null;
        }
        TreeNode leftRoot = invertTree(root.left);
        TreeNode rightRoot = invertTree(root.right);
        root.left = rightRoot;
        root.right = leftRoot;
        return root;
    }
}
