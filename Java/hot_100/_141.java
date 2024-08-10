package Java.hot_100;

public class _141 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head.next;
        // 快慢指针。
        while (!slow.equals(fast)) {
            if (fast == null) return false;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        return true;
    }
}
