package Java.hot_100;

import java.util.HashMap;
import java.util.Map;

public class _146 {


}

class DoublyNode {
    int key;
    int val;
    DoublyNode pre, next;

    public DoublyNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}


class LRUCache {
    int capacity;
    Map<Integer, DoublyNode> map;
    DoublyNode dummy;
    DoublyNode tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.dummy = new DoublyNode(-1, -1);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        DoublyNode node = map.get(key);
        DoublyNode next = node.next;
        DoublyNode pre = node.pre;
        pre.next = next;
        if (next != null) {
            next.pre = pre;
        } else {
            // 如果 next 是 null，说明原来 node 是尾结点。
            this.tail = pre;
        }

        DoublyNode dummyNext = dummy.next;
        if (dummyNext != null) {
            dummyNext.pre = node;
        } else {
            // 如果 dummyNext 是 null，说明新 node 是尾结点。
            this.tail = node;
        }
        dummy.next = node;
        node.pre = dummy;
        node.next = dummyNext;

        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoublyNode node = map.get(key);
            node.val = value;
            // 更新 value。

            DoublyNode next = node.next;
            DoublyNode pre = node.pre;
            pre.next = next;
            if (next != null) {
                next.pre = pre;
            } else {
                this.tail = pre;
            }

            DoublyNode dummyNext = dummy.next;
            if (dummyNext != null) {
                dummyNext.pre = node;
            } else {
                this.tail = node;
            }

            dummy.next = node;
            node.pre = dummy;
            node.next = dummyNext;

            return;
        }
        DoublyNode newNode = new DoublyNode(key, value);
        map.put(key, newNode);
        if (map.size() > capacity) {
            // 删除链表的尾结点。
            int tailKey = this.tail.key;
            map.remove(tailKey);
            DoublyNode pre = this.tail.pre;
            pre.next = null;
            this.tail = pre;

            DoublyNode dummyNext = dummy.next;
            if (dummyNext != null) {
                dummyNext.pre = newNode;
            } else {
                this.tail = newNode;
            }

            dummy.next = newNode;
            newNode.pre = dummy;
            newNode.next = dummyNext;

            return;
        }
        DoublyNode dummyNext = dummy.next;
        if (dummyNext != null) {
            dummyNext.pre = newNode;
        } else {
            this.tail = newNode;
        }

        dummy.next = newNode;
        newNode.pre = dummy;
        newNode.next = dummyNext;

    }
}

/*
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */