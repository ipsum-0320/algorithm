package Java.hot_100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _199 {
    public List<Integer> rightSideView(TreeNode root) {
        // 层序遍历秒杀。
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int right = -1;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                right = node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(right);
        }
        return res;
    }
}
