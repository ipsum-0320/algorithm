package Java.hot_100;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
      next = null;
    }

}

public class _160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 使用双指针完成。
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }


}
