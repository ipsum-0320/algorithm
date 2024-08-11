package Java.hot_100;

public class _142 {
    public ListNode detectCycle(ListNode head) {
        // 快慢指针实现，快指针走两步，慢指针走一步，相遇后，快指针回到起点，两个指针都走一步，再次相遇的地方就是环的入口。
        ListNode fast = head, slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (fast != null && fast == slow) {
                // 记得判断 fast 不为 null。
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}
