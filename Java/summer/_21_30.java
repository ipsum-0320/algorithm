package Java.summer;

public class _21_30 {
    // 240-24
    // 240
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            int value = matrix[i][j];
            if (value > target) i--;
            else if (value < target) j++;
            else return true;
        }
        return false;
    }

    // 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode firstA = headA, firstB = headB;
        while (true) {
            if (headA == headB) break;
            // 解决一开始就交接的情况。
            headA = headA.next;
            headB = headB.next;
            if (headA == headB) break;
            // 先检查 headA、headB 是否相同。
            if (headA == null) headA = firstB;
            if (headB == null) headB = firstA;
        }
        return headA;
    }

    // 206
    public ListNode reverseList(ListNode head) {
        // 链表最重要的就是建模。
        // 翻转需要知道 => 前一个、中间的、后一个。
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode pre = null, cur = head, next = head.next;
        while (next != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
        // 不能返回 cur，因为 cur 最后被赋值为了 next，最后 next 为 null。
    }

    public ListNode reverseList_2(ListNode head) {
        // 链表最重要的就是建模。
        // 翻转需要知道 => 前一个、中间的、后一个。
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 234
    public boolean isPalindrome(ListNode head) {
        // 快慢指针找链表中点。
        // 翻转链表判断是否为回文链表，不需要管奇数还是偶数。
        // 只要在翻转的那一段在遍历结束之前值都一样就行。
        ListNode fast = head, slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        ListNode midNode = reverse(slow);
        // 中点链表是更短的哪一个。
        while (midNode != null) {
            if (midNode.val != head.val) return false;
            midNode = midNode.next;
            head = head.next;
        }
        return true;
    }
    public ListNode reverse(ListNode node) {
        ListNode pre = null, cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 141
    public boolean hasCycle(ListNode head) {
        // 环形链表，使用快慢指针。
        if (head == null || head.next == null) return false;
        ListNode fast = head.next, slow = head;
        // 一开始不能让 fast 和 slow 相同。
        while (fast != null) {
            // 必须是 fast，不能是 slow，否则 fast.next 会报错。
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        return false;
    }

    // 142


}
