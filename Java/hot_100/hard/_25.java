package Java.hot_100.hard;

public class _25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        // pre head ... tail next
        ListNode dummy = new ListNode(0), pre = dummy;
        pre.next = head;
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }

        while (length >= k) {
            ListNode start = pre.next;
            for (int i = 1; i < k; i++) {
                // start 已经算是第一个了，所以需要从 i = 1 开始。
                start = start.next;
            }
            ListNode tail = start;
            ListNode next = tail.next;
            start = pre.next;
            tail.next = null;
            reverse(start);
            pre.next = tail;
            start.next = next;
            pre = start;
            length -= k;
        }
        return dummy.next;
    }

    public void reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }
}
