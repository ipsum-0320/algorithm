package Java.summer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class _41_50 {
    // 102-124
    // 102
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 使用队列解决。
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> single = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                single.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(single);
        }
        return res;
    }

    // 108
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        // 位运算的优先级是最靠后的，因此需要加括号。
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    // 98-使用 map 的方法。
    class Solution {
        Map<TreeNode, Long> minMap = new HashMap<>();
        Map<TreeNode, Long> maxMap = new HashMap<>();

        public boolean isValidBST(TreeNode root) {
            // 使用递归来验证，二叉搜索树不仅要保证根节点和左右子树的根节点的大小关系，
            // 更要保证根节点和左右子树中最大值和最小值的大小关系。
            if (root == null) {
                return true;
            }
            minMap.put(null, Long.MAX_VALUE);
            maxMap.put(null, Long.MIN_VALUE);
            getMin(root);
            getMax(root);
            return helper(root);
        }

        public Long getMin(TreeNode node) {
            // 获取以 node 为根节点的树的最小值。
            if (node == null) {
                return Long.MAX_VALUE;
            }
            Long leftMin = getMin(node.left);
            Long rightMin = getMin(node.right);
            Long min = Math.min(node.val, Math.min(leftMin, rightMin));
            minMap.put(node, min);
            return min;
        }

        public Long getMax(TreeNode node) {
            // 获取以 node 为根节点的树的最大值。
            if (node == null) {
                return Long.MIN_VALUE;
            }
            Long leftMax = getMax(node.left);
            Long rightMax = getMax(node.right);
            Long max = Math.max(node.val, Math.max(leftMax, rightMax));
            maxMap.put(node, max);
            return max;
        }

        public boolean helper(TreeNode node) {
            // 判断 node 是否是一个二叉搜索树。
            if (node == null) {
                return true;
            }
            boolean left = helper(node.left);
            boolean right = helper(node.right);
            return left && right && node.val > maxMap.get(node.left) && node.val < minMap.get(node.right);
        }
    }

    // 98-使用上下限的方法。
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean helper(TreeNode node, Long lower, Long upper) {
        if (node == null) {
            return true;
        }
        boolean cur = node.val > lower && node.val < upper;
        return cur && helper(node.left, lower, (long) node.val) && helper(node.right, (long) node.val, upper);
    }

    // 230-优先队列。
    public int kthSmallest(TreeNode root, int k) {
        // 可以使用中序遍历，也可以是用堆。
        PriorityQueue<TreeNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        step(root, heap);
        while (k != 0) {
            heap.poll();
            k--;
        }
        return heap.peek().val;
    }

    public void step(TreeNode root, PriorityQueue<TreeNode> heap) {
        if (root == null) {
            return;
        }
        heap.add(root);
        step(root.left, heap);
        step(root.right, heap);
    }

    // 230-二分查找。
    public int kthSmallest_2(TreeNode root, int k) {
        Map<TreeNode, Integer> numMap = new HashMap<>();
        numMap.put(null, 0);
        getNum(root, numMap);
        while (root != null) {
            // 二分查找的精髓是每次扔掉一半的可能性。
            int num = numMap.get(root.left);
            // 查看左子树有多少 num。
            if (num == k - 1) {
                // 如果左子树已经有 num - 1 个节点，那么 root 就是第 k 个数据。
                return root.val;
            } else if (num < k - 1) {
                // num < k - 1，说明第 k 个数据在右子树。
                root = root.right;
                k -= (num + 1);
                // 因为 num 只考虑子树，因此需要更新 k 值。
            } else {
                // num > k - 1，说明第 k 个数据在左子树。
                root = root.left;
            }
        }
        return -1;
    }

    public int getNum(TreeNode node, Map<TreeNode, Integer> numMap) {
        if (node == null) {
            return 0;
        }
        int left = getNum(node.left, numMap);
        int right = getNum(node.right, numMap);
        int sum = 1 + left + right;
        numMap.put(node, sum);
        return sum;
    }

    // 199
    public List<Integer> rightSideView(TreeNode root) {
        // 层次遍历。
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (i == length - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    // 114
    public void flatten(TreeNode root) {
        // flatten 函数用于将树拉为链表。
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        // 将 left 放到 right。
        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left != null) {
            // 需要找到 left 的最后一个节点。
            while (left.right != null) {
                left = left.right;
            }

            root.right = root.left;
            left.right = right;
            root.left = null; // 删除左节点。
        }
    }

    // 105
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 通过递归从前序和中序构建二叉树。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(new TreeNode(0), preorder, inorder, map, 0, inorder.length - 1);
    }
    public TreeNode helper(TreeNode i, int[] preorder, int[] inorder, Map<Integer, Integer> map, int left, int right) {
        if (i.val >= preorder.length || left > right) {
            return null;
        }
        int rootVal = preorder[i.val];
        TreeNode root = new TreeNode(rootVal);
        int index = map.get(rootVal);
        i.val++;
        root.left = helper(i, preorder, inorder, map, left, index - 1);
        root.right = helper(i, preorder, inorder, map, index + 1, right);
        return root;
    }

    // 437
    public int pathSum(TreeNode root, int targetSum) {
        // 标准的前缀和。
        Map<Long, Integer> map = new HashMap<>();
        TreeNode res = new TreeNode(0);
        map.put(0L, 1);
        // 记得初始化 0, 1。
        preorder(root, 0, targetSum, map, res);
        return res.val;
    }
    public void preorder(TreeNode root, long sum, long target, Map<Long, Integer> map, TreeNode res) {
        if (root == null) return;
        sum += root.val;
        res.val += map.getOrDefault(sum - target, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        preorder(root.left, sum, target, map, res);
        preorder(root.right, sum, target, map, res);
        map.put(sum, map.getOrDefault(sum, 0) - 1);
    }

    // 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 前序遍历去找 p 和 q 的所有祖先。
        List<TreeNode> pList = new ArrayList<>();
        List<TreeNode> qList = new ArrayList<>();
        preorderForCommon(root, q, new TreeNode(0), qList);
        preorderForCommon(root, p, new TreeNode(0), pList);
        Set<Integer> set = new HashSet<>();
        for (TreeNode treeNode : pList) {
            set.add(treeNode.val);
        }
        for (TreeNode node : qList) {
            if (set.contains(node.val)) return node;
        }
        return null;
    }
    public void preorderForCommon(TreeNode root, TreeNode target, TreeNode isSearched, List<TreeNode> commonList) {
        if (root == null) return;
        if (root == target) {
            isSearched.val = 1;
            commonList.add(root);
            return;
        }
        preorderForCommon(root.left, target, isSearched, commonList);
        if (isSearched.val == 1) {
            commonList.add(root);
            return;
        }
        preorderForCommon(root.right, target, isSearched, commonList);
        if (isSearched.val == 1) {
            commonList.add(root);
        }
    }

    // 236，直接使用存储每个节点的父节点。
    public TreeNode lowestCommonAncestor_2(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        preorderForCommon_2(root, map);
        Set<TreeNode> qRoot = new HashSet<>();
        while (q != null) {
            qRoot.add(q);
            q = map.get(q);
        }
        while (p != null) {
            if (qRoot.contains(p)) return p;
            p = map.get(p);
        }
        return null;
    }
    public void preorderForCommon_2(TreeNode node, Map<TreeNode, TreeNode> map) {
        if (node.left != null) {
            map.put(node.left, node);
            preorderForCommon_2(node.left, map);
        }
        if (node.right != null) {
            map.put(node.right, node);
            preorderForCommon_2(node.right, map);
        }
    }

    // 124
    public int maxPathSum(TreeNode root) {
        TreeNode max = new TreeNode(Integer.MIN_VALUE);
        maxHelper(root, max);
        return max.val;
    }
    public int maxHelper(TreeNode root, TreeNode max) {
        if (root.left != null && root.right != null) {
            int left = maxHelper(root.left, max);
            int right = maxHelper(root.right, max);
            int returnMax = Math.max(root.val, Math.max(root.val + left, root.val + right));
            // 如果是 root.val + left + right，那么就不能够 returnMax 了。
            int curMax = Math.max(returnMax, Math.max(left, Math.max(right, root.val + left + right)));
            max.val = Math.max(max.val, curMax);
            return returnMax;
        } else if (root.left == null && root.right == null) {
            int curMax = root.val;
            max.val = Math.max(max.val, curMax);
            return curMax;
        } else if (root.left != null) {
            int left = maxHelper(root.left, max);
            int returnMax = Math.max(root.val, root.val + left);
            int curMax =  Math.max(returnMax, left);
            max.val = Math.max(max.val, curMax);
            return returnMax;
        } else {
            int right = maxHelper(root.right, max);
            int returnMax = Math.max(root.val, root.val + right);
            int curMax =  Math.max(returnMax, right);
            max.val = Math.max(max.val, curMax);
            return returnMax;
        }
    }

    // 下面给出一种更优雅的实现方式。
    public int maxPathSum_2(TreeNode root) {
        TreeNode max = new TreeNode(Integer.MIN_VALUE);
        maxHelper_2(root, max);
        return max.val;
    }
    public int maxHelper_2(TreeNode node, TreeNode res) {
        if (node == null) return 0;
        int left = Math.max(maxHelper_2(node.left, res), 0);
        int right = Math.max(maxHelper_2(node.right, res), 0);
        // left、right（已经在下一层的 maxValue 中考虑过了）
        res.val = Math.max(res.val, node.val + left + right);
        return node.val + Math.max(left, right);
        // 只考虑直线的贡献。
    }

}
