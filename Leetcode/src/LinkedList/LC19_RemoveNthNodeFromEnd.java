package Leetcode.src.LinkedList;

public class LC19_RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (n == 1 && head.next == null) {
            return null;
        }

        ListNode pre = new ListNode();
        ListNode cur = pre;
        pre.next = head;
        int count = 1;
        int len = getListLen(pre);

        while (count < len - n) {
            cur = cur.next;
            count++;
        }
        cur.next = cur.next.next;

        return pre.next;
    }

    public int getListLen(ListNode head) {
        int ans = 0;
        while (head != null) {
            head = head.next;
            ans++;
        }
        return ans;
    }

    public ListNode removeNthFromEnd_2(ListNode head, int n) {
        if (n == 1 && head.next == null) {
            return null;
        }

        ListNode pre = new ListNode();
        pre.next = head;

        int cnt = 0;
        ListNode quick = pre, slow = pre;
        while (cnt <= n) {
            quick = quick.next;
            cnt++;
        }

        while (quick != null) {
            quick = quick.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return pre.next;

    }
}
