package Java.hot_100.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class _23 {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null)
                queue.add(list);
        }

        ListNode dummy = new ListNode(0), cur = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.remove();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) queue.add(node.next);
        }
        return dummy.next;
    }

}
