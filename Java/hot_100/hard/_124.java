package Java.hot_100.hard;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxValue(root);
        return res;
    }

    public int maxValue(TreeNode root) {
        // 以 root 为根节点可以贡献多少值。
        if (root == null) return 0;
        // 【left、right（已经在上一层的 maxValue 中考虑过了，就是所谓的 left + node、right + node）】、
        // left + node、right + node、left + node + right。
        int left = Math.max(maxValue(root.left), 0);
        int right = Math.max(maxValue(root.right), 0);
        // 负贡献不考虑。
        res = Math.max(res, root.val + left + right);
        return root.val + Math.max(left, right);
        // 只考虑直线贡献。
    }
}