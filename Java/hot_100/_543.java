package Java.hot_100;

public class _543 {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // 递归，计算左右子树的深度，然后求最大值。
        helper(root);
        return this.max;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        this.max = Math.max(this.max, left + right);
        return Math.max(left, right) + 1;
    }
}
