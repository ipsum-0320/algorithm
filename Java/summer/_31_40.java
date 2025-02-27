package Java.summer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class _31_40 {
    // 25-543
    // 25：K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head), pre = dummy, start = head, preEnd = dummy;
        int step = 0;
        while (head != null) {
            pre = pre.next;
            head = head.next;
            step++;
            if (step % k == 0) {
                // preEnd | start ... pre | head
                pre.next = null;
                reverse(start);
                // preEnd | pre ... start | head
                start.next = head;
                preEnd.next = pre;
                preEnd = start;
                pre = start;
                start = head;
            }
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        // 单元测试 reverse 没有问题。
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 138
    public Node copyRandomList(Node head) {
        // 用 map 的做法去做。
        Map<Node, Node> map = new HashMap<>();
        Node first = head;

        while (first != null) {
            Node copy = new Node(first.val);
            map.put(first, copy);
            first = first.next;
        }

        Node copyHead = map.get(head);
        first = head;
        while (first != null) {
            Node copy = map.get(first);
            copy.next = map.get(first.next);
            copy.random = map.get(first.random);
            first = first.next;
        }
        return copyHead;
    }
    public Node copyRandomList_2(Node head) {
        if (head == null) return null;
        Node first = head;
        while (first != null) {
            Node copy = new Node(first.val);
            Node next = first.next;
            copy.next = next;
            first.next = copy;
            first = next;
        }
        first = head;
        Node newHead = first.next;
        // 必须先修改 random，再修改 next，然后在修改 next 的同时还原 head。
        // 否则会造成 random 访问不到克隆节点，或者还原的时候找不到原节点。
        while (first != null) {
            Node copy = first.next;
            Node next = first.next.next;
            Node random = first.random;
            if (random == null) copy.random = null;
            else copy.random = random.next;
            first = next;
        }
        first = head;
        // 恢复 first 原链表，同时修改 copy 的 next。
        while (first != null) {
            Node copy = first.next;
            Node next = copy.next;
            if (next == null) copy.next = null;
            else copy.next = next.next;
            first.next = next;
            first = next;
        }
        return newHead;
    }

    // 148
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null, slow = head, fast = head;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        pre.next = null;
        ListNode left = sortList(head), right = sortList(slow);
        return merge(left, right);
    }
    public ListNode merge(ListNode p1, ListNode p2) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val >= p2.val) {
                pre.next = p2;
                pre = p2;
                p2 = p2.next;
            } else {
                pre.next = p1;
                pre = p1;
                p1 = p1.next;
            }
        }
        if (p1 != null) pre.next = p1;
        if (p2 != null) pre.next = p2;

        return dummy.next;
    }

    // 23
    public ListNode mergeKLists(ListNode[] lists) {
        // 可以有序合并，然后去两两合并，但是复杂度太高，使用优先队列去解决。
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        // 最小堆。
        for (ListNode node : lists) {
            if (node != null)
                heap.add(node);
        }
        ListNode dummy = new ListNode(-1), pre = dummy;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            ListNode next = min.next;
            min.next = null;
            pre.next = min;
            pre = min;
            if (next != null)
                heap.add(next);
        }

        return dummy.next;
    }

    // 146
    class LRUCache {
        // map + 双向链表解决。

        private Map<Integer, DoubleList> map = new HashMap<>();
        private int capacity = 0;
        private DoubleList tail = null;
        private DoubleList dummy = new DoubleList(-1, -1);

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            // 有 key。
            DoubleList node = map.get(key);
            // node 是首节点。
            if (dummy.next == node) return node.val;
            // node 是尾结点。
            if (this.tail == node) {
                DoubleList tailPre = this.tail.pre;
                tailPre.next = null;
                this.tail.pre = null;
                this.tail = tailPre;
            } else {
                // node 断链重接。
                DoubleList pre = node.pre;
                DoubleList next = node.next;
                node.pre = null;
                node.next = null;
                pre.next = next;
                next.pre = pre;
            }

            // head 断链重接。
            DoubleList head = dummy.next;
            dummy.next = node;
            node.pre = dummy;
            node.next = head;
            head.pre = node;

            return node.val;
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                // 没有 key。
                DoubleList node = new DoubleList(key, value);
                map.put(key, node);

                // 是第一个。
                DoubleList head = dummy.next;
                if (head == null) {
                    dummy.next = node;
                    node.pre = dummy;
                    this.tail = node;
                } else {
                    // 不是第一个。
                    dummy.next = node;
                    node.next = head;
                    node.pre = dummy;
                    head.pre = node;
                }

                // 检查是否需要过期。
                if (map.size() > capacity) {
                    // 需要抛弃最后一个节点，capacity >= 1。
                    map.remove(this.tail.key);
                    DoubleList tailPre = this.tail.pre;
                    tailPre.next = null;
                    this.tail.pre = null;
                    this.tail = tailPre;
                }

                return;
            }

            // 有 key。
            DoubleList node = map.get(key);
            node.val = value;
            // node 是首节点。
            if (dummy.next == node) return;
            // node 是尾结点。
            if (this.tail == node) {
                DoubleList tailPre = this.tail.pre;
                tailPre.next = null;
                this.tail.pre = null;
                this.tail = tailPre;
            } else {
                // node 断链重接。
                DoubleList pre = node.pre;
                DoubleList next = node.next;
                node.pre = null;
                node.next = null;
                pre.next = next;
                next.pre = pre;
            }

            DoubleList head = dummy.next;
            dummy.next = node;
            node.pre = dummy;
            node.next = head;
            head.pre = node;
        }
    }

    // 94
    public List<Integer> inorderTraversal(TreeNode root) {
        // 迭代法解决中序遍历。
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            // root 是根 mid 的右节点，或者 stack 不为空就继续遍历。
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode mid = stack.pop();
            res.add(mid.val);
            root = mid.right;
        }
        return res;
    }

    // 104
    public int maxDepth(TreeNode root) {
        // 其实这种递归和动态规划很像。
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // 226
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        // 交换两棵子树的位置。
        TreeNode left = invertTree(root.left);
        // 禁止直接 root.right = invertTree(root.left)，这会修改数据结构。
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 101
    public boolean isSymmetric(TreeNode root) {
        // 模拟出另外一个一棵树，逐个比较是否相同。
        if (root == null) return true;
        return helper(root.left, root.right);
    }
    public boolean helper(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        else if (node1 != null && node2 != null) {
            return node1.val == node2.val && helper(node1.left, node2.right) && helper(node1.right, node2.left);
            // helper 的作用就是保证 node1 和 node2 完全一样 <=> 本节点一样 && 左节点一样 && 右节点一样。
            // 只不过对于对称二叉树来说，保证 左1 == 右2，左2 == 右1 就可以了。
        } else return false;
    }

    // 543
    class Solution {
        int res = 0;
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            int left = helper(root.left);
            int right = helper(root.right);
            res = Math.max(res, 1 + left + right);
            return res - 1;
        }
        public int helper(TreeNode root) {
            if (root == null) return 0;
            int left = helper(root.left);
            int right = helper(root.right);
            res = Math.max(res, 1 + left + right);
            return 1 + Math.max(left, right);
        }
    }
}


