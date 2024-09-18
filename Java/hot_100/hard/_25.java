package Java.hot_100.hard;

public class _25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0), pre = dummy, cur = head;
        pre.next = cur;
        while (cur != null) {
            ListNode curH = cur, curT = null;
            for (int i = 0; i < k; i++) {
                if (curH == null) return dummy.next;
                curT = curH;
                curH = curH.next;
            }
            ListNode next = curT.next;
            ListNode[] reverse = myReverse(cur, curT);
            ListNode newH = reverse[0], newT = reverse[1];
            pre.next = newH;
            newT.next = next;
            cur = next;
            pre = newT;
        }
        return dummy.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode cur = head;
        while (pre != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
    }

}
