package Java.hot_100;

public class _2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 模拟加法。
        int l1L = 0, l2L = 0;
        ListNode l1Temp = l1, l2Temp = l2;
        while (l1Temp != null) {
            l1Temp = l1Temp.next;
            l1L++;
        }
        while (l2Temp != null) {
            l2Temp = l2Temp.next;
            l2L++;
        }
        int isAdd = 0;
        l1Temp = l1;
        l2Temp = l2;
        if (l1L >= l2L) {
            while (l1Temp != null || isAdd == 1) {
                if (l2Temp == null) {
                    l1Temp.val = l1Temp.val + isAdd;
                } else {
                    l1Temp.val = l1Temp.val + l2Temp.val + isAdd;
                }
                isAdd = 0;
                if (l1Temp.val >= 10) {
                    l1Temp.val = l1Temp.val - 10;
                    isAdd = 1;
                }
                if (l1Temp.next == null && isAdd == 1) {
                    l1Temp.next = new ListNode(0);
                }
                l1Temp = l1Temp.next;
                if (l2Temp != null) {
                    l2Temp = l2Temp.next;
                }
            }
            return l1;
        } else {
            while (l2Temp != null || isAdd == 1) {
                if (l1Temp == null) {
                    l2Temp.val = l2Temp.val + isAdd;
                } else {
                    l2Temp.val = l1Temp.val + l2Temp.val + isAdd;
                }
                isAdd = 0;
                if (l2Temp.val >= 10) {
                    l2Temp.val = l2Temp.val - 10;
                    isAdd = 1;
                }
                if (l2Temp.next == null && isAdd == 1) {
                    l2Temp.next = new ListNode(0);
                }
                l2Temp = l2Temp.next;
                if (l1Temp != null) {
                    l1Temp = l1Temp.next;
                }
            }
            return l2;
        }
    }

}
