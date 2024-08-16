package Java.hot_100;

public class _108 {
    int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        // 递归，取中点构建平衡的二叉搜索树。
        this.nums = nums;
        return helper(0, nums.length - 1);
    }

    public TreeNode helper(int left, int right) {
        if (left > right) {
            // 递归终止条件，注意是left > right，
            // 而不是left >= right，因为left == right时，还需要构建一个节点。
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode leftNode = helper(left, mid - 1);
        TreeNode rightNode = helper(mid + 1, right);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

}
