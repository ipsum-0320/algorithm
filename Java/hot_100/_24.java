package Java.hot_100;

public class _24 {
    public ListNode swapPairs(ListNode head) {
        // 递归。
        if (head == null || head.next == null) {
            // 交换两个节点，必须要有两个节点。
            // 如果只有一个节点或者没有节点，直接返回。
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public ListNode swapPairs_2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        // pre -> node1 -> node2。
        while (pre.next != null && pre.next.next != null) {
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;
            pre.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            pre = node1;
        }
        return dummy.next;
    }
}
