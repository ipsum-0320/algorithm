package Java.hot_100;

public class _114 {
    public void flatten(TreeNode root) {
        // 递归，左右子树进行 flatten。
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left != null) {
            // 如果左子树不为空，将左子树插入到右子树的前面。
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;
            root.right = root.left;
            root.left = null;
        }
    }
}
