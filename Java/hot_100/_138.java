package Java.hot_100;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class _138 {
    public Node copyRandomList(Node head) {
        Node dummyNode = new Node(-1), pre = dummyNode, cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            Node node = new Node(cur.val);
            map.put(cur, node);
            pre.next = node;
            pre = pre.next;
            cur = cur.next;
        }
        map.put(null, null);
        cur = head;
        Node newCur = dummyNode.next;
        while (cur != null) {
            newCur.random = map.get(cur.random);
            cur = cur.next;
            newCur = newCur.next;
        }
        return dummyNode.next;
    }
}
