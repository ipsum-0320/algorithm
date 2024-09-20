package Java.hot_100;

import java.util.HashMap;
import java.util.Map;

public class _146 {

}

class DoublyNode {
    int val;
    int key; // 为了容量不够的时候删除节点。
    DoublyNode pre, next;

    public DoublyNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}


class LRUCache {
    int capacity; // 容量。
    DoublyNode dummy; // 头结点。
    Map<Integer, DoublyNode> map;

    DoublyNode tail; // 尾结点，为了容量不足时删除。

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.dummy = new DoublyNode( -1, -1);
        this.map = new HashMap<>(capacity);
        tail = null;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        // pre node next
        else {
            DoublyNode node = map.get(key);
            DoublyNode pre = node.pre, next = node.next;
            pre.next = next;
            if (next != null) next.pre = pre;
            else tail = pre;
            // next == null 说明 node 是尾结点。

            DoublyNode head = dummy.next;
            dummy.next = node;
            if (head != null) head.pre = node;
            else tail = node;
            node.pre = dummy;
            node.next = head;
            return map.get(key).val;
        }

    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            DoublyNode node = new DoublyNode(key, value);
            map.put(key, node);

            DoublyNode head = dummy.next;
            dummy.next = node;
            if (head != null)
                head.pre = node;
            else tail = node;
            node.pre = dummy;
            node.next = head;

            if (map.size() > capacity) {
                // 删除。
                map.remove(tail.key);
                DoublyNode tailPre = tail.pre;
                tail = tailPre;
                tailPre.next = null;
            }
        } else {
            DoublyNode node = map.get(key);
            node.val = value;

            DoublyNode pre = node.pre, next = node.next;
            pre.next = next;
            if (next != null)
                next.pre = pre;
            else tail = pre;


            DoublyNode head = dummy.next;
            dummy.next = node;
            if (head != null)
                head.pre = node;
            else tail = node;
            node.pre = dummy;
            node.next = head;
        }
    }
}

/*
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */