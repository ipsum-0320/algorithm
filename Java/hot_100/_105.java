package Java.hot_100;

import java.util.HashMap;
import java.util.Map;

public class _105 {

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 递归，根据前序遍历和中序遍历构建二叉树。
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        if (preL > preR) {
            // preR - preL == inR - inL。
            return null;
        }
        int rootVal = preorder[preL];
        TreeNode root = new TreeNode(rootVal);
        int index = map.get(rootVal);
        root.left = helper(preorder, preL + 1, preL + index - inL, inorder, inL, index - 1);
        root.right = helper(preorder, preL + index - inL + 1, preR, inorder, index + 1, inR);
        return root;
    }

}
