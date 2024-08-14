package Java.hot_100;

public class _148 {
    public ListNode sortList(ListNode head) {
        // 归并排序的第一步是什么？取中点
        if (head == null || head.next == null) {
            return head;
            // 递归出口。
        }
        ListNode fast = head, slow = head, pre = new ListNode(-1);
        pre.next = head;
        // 用于定位中间节点的前置节点。
        while (fast != null) {
            pre = pre.next;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        pre.next = null;
        // 断开链表。
        ListNode right = sortList(slow);
        ListNode left = sortList(head);
        return merge(left, right);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        // 合并两个有序链表。
        ListNode dummy = new ListNode(-1), pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
