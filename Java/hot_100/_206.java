package Java.hot_100;

public class _206 {
    public ListNode reverseList(ListNode head) {
        // 迭代解法。
        ListNode pre = null, cur = head;
        // 存前，取后。
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode helper(ListNode head, ListNode newHead) {
        if (head == null || head.next == null) {
            newHead.next = head;
            return head;
        }
        ListNode p = helper(head.next, newHead);
        p.next = head;
        head.next = null;
        return head;
    }

    public ListNode reverseList_2(ListNode head) {
        // 递归解法，就是遍历到最后一个节点再进行处理。
        ListNode newHead = new ListNode(0);
        // 需要有一个节点存储最新的头节点。
        helper(head, newHead);
        return newHead.next;
    }
}
