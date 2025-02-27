package Java.summer;

public class _21_30 {
    // 240-24
    // 240
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            int value = matrix[i][j];
            if (value > target) i--;
            else if (value < target) j++;
            else return true;
        }
        return false;
    }

    // 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode firstA = headA, firstB = headB;
        while (true) {
            if (headA == headB) break;
            // 解决一开始就交接的情况。
            headA = headA.next;
            headB = headB.next;
            if (headA == headB) break;
            // 先检查 headA、headB 是否相同。
            if (headA == null) headA = firstB;
            if (headB == null) headB = firstA;
        }
        return headA;
    }

    // 206
    public ListNode reverseList(ListNode head) {
        // 链表最重要的就是建模。
        // 翻转需要知道 => 前一个、中间的、后一个。
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode pre = null, cur = head, next = head.next;
        while (next != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
        // 不能返回 cur，因为 cur 最后被赋值为了 next，最后 next 为 null。
    }

    public ListNode reverseList_2(ListNode head) {
        // 链表最重要的就是建模。
        // 翻转需要知道 => 前一个、中间的、后一个。
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 234
    public boolean isPalindrome(ListNode head) {
        // 快慢指针找链表中点。
        // 翻转链表判断是否为回文链表，不需要管奇数还是偶数。
        // 只要在翻转的那一段在遍历结束之前值都一样就行。
        ListNode fast = head, slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        ListNode midNode = reverse(slow);
        // 中点链表是更短的哪一个。
        while (midNode != null) {
            if (midNode.val != head.val) return false;
            midNode = midNode.next;
            head = head.next;
        }
        return true;
    }
    public ListNode reverse(ListNode node) {
        ListNode pre = null, cur = node;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 141
    public boolean hasCycle(ListNode head) {
        // 环形链表，使用快慢指针。
        if (head == null || head.next == null) return false;
        ListNode fast = head.next, slow = head;
        // 一开始不能让 fast 和 slow 相同。
        while (fast != null) {
            // 必须是 fast，不能是 slow，否则 fast.next 会报错。
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
        }
        return false;
    }

    // 142
    public ListNode detectCycle(ListNode head) {
        ListNode pA = head, pB = head;
        while (pA != null) {
            pA = pA.next;
            pB = pB.next;
            if (pA != null) pA = pA.next;
            if (pA == pB) break;
        }
        if (pA == null) return null;
        ListNode pC = head;
        while (pC != pB) {
            pC = pC.next;
            pB = pB.next;
        }
        return pC;
    }

    // 21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                pre.next = list1;
                pre = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                pre = list2;
                list2 = list2.next;
            }
        }

        if (list1 != null) pre.next = list1;
        if (list2 != null) pre.next = list2;

        return dummy.next;
    }

    // 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        int delta = 0;
        while (l1 != null || l2 != null) {
            int res = delta;
            if (l1 != null) {
                res += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                res += l2.val;
                l2 = l2.next;
            }
            delta = res >= 10 ? 1 : 0;
            res = res % 10;
            pre.next = new ListNode(res);
            pre = pre.next;
        }
        if (delta == 1) {
            pre.next = new ListNode(delta);
        }
        return dummy.next;
    }

    // 2
    public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        int L1 = 0, L2 = 0;
        while (p1 != null) {
            L1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            L2++;
            p2 = p2.next;
        }

        if (L2 >= L1) {
            ListNode head = l2, pre = null;
            int delta = 0;
            while (l2 != null) {
                l2.val = l2.val + delta;
                if (l1 != null) {
                    l2.val = l2.val + l1.val;
                    l1 = l1.next;
                }
                if (l2.val >= 10) {
                    delta = 1;
                    l2.val = l2.val % 10;
                } else delta = 0;
                pre = l2;
                l2 = l2.next;
            }
            if (delta == 1) pre.next = new ListNode(delta);
            return head;
        }

        ListNode head = l1, pre = null;
        int delta = 0;
        while (l1 != null) {
            l1.val = l1.val + delta;
            if (l2 != null) {
                l1.val = l1.val + l2.val;
                l2 = l2.next;
            }
            if (l1.val >= 10) {
                delta = 1;
                l1.val = l1.val % 10;
            } else delta = 0;
            pre = l1;
            l1 = l1.next;
        }
        if (delta == 1) pre.next = new ListNode(delta);
        return head;
    }

    // 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head), fast = head, slow = head, pre = dummy;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            pre = pre.next;
        }
        pre.next = slow.next;
        slow.next = null;
        return dummy.next;
    }

    // 24
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1, head), pre = dummy;
        ListNode first = head, second = head.next;
        while (first != null && second != null) {
            pre.next = second;
            ListNode after = second.next;
            second.next = first;
            first.next = after;
            pre = first;
            // 记得更新一下 pre。
            first = after;
            if (first != null) second = after.next;
        }
        return dummy.next;
    }
}
