package Java.max_300;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}


public class _143 {

    public void reorderList(ListNode head) {
        merge(head, reverse(findMid(head)));
    }

    public ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head, pre = null;
        // pre 用于断开链表。
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        pre.next = null;
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public void merge(ListNode node1, ListNode node2) {
        // 直接交叉合并。
        ListNode dummy = new ListNode(0), cur = dummy;
        while (node1 != null && node2 != null) {
            cur.next = node1;
            node1 = node1.next;
            cur = cur.next;

            cur.next = node2;
            node2 = node2.next;
            cur = cur.next;
        }
        cur.next = node1 == null ? node2 : node1;
    }
}
