package Java.hot_100;

public class _19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 双指针。
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy, fast = head;
        // 引入 dummy 节点，方便删除头节点。
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
