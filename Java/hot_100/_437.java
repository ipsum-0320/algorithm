package Java.hot_100;

import java.util.HashMap;
import java.util.Map;

public class _437 {

    Map<Long, Integer> map = new HashMap<>();
    int res = 0;
    long target;


    public int pathSum(TreeNode root, int targetSum) {
        // 递归，遍历每个节点，以每个节点为起点，寻找路径和为 targetSum 的路径数。
        // 前缀和的两个重点：1. 以当前节点为终点的路径和；2. 以前驱节点为终点的路径和。
        map.put(0L, 1);
        this.target = targetSum;
        helper(root, 0);
        return res;
    }

    public void helper(TreeNode root, long sum) {
        if (root == null) {
            return;
        }
        if (map.containsKey(sum + root.val - target)) {
            res += map.get(sum + root.val - target);
        }
        map.put(sum + root.val, map.getOrDefault(sum + root.val, 0) + 1);
        helper(root.left, sum + root.val);
        helper(root.right, sum + root.val);
        map.put( sum + root.val, map.get( sum + root.val) - 1);
    }

}
