package Java.hot_100;

import java.util.ArrayList;
import java.util.List;

public class _234 {
    public boolean isPalindrome(ListNode head) {
        // 使用辅助数组。
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public ListNode helper(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public boolean isPalindrome_2(ListNode head) {
        // 快慢指针找到中点，然后反转后半部分链表，再比较。
        ListNode slow = head, fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = helper(slow);
        while (head != null && newHead != null) {
            if (head.val != newHead.val) return false;
            head = head.next;
            newHead = newHead.next;
        }
        return true;
    }
}
