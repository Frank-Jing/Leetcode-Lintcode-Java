package Leetcode.src;

public class LC92_ReverseLinkedList_II {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null) return head;
        if(left == right) return head;

        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode before = null;
        ListNode after = null;

        int i = 0;
        ListNode cur = pre;
        while(i++<right){
            if(i == left){
                before = cur;
            }
            cur = cur.next;
        }
        after = cur.next;
        cur.next = null;

        ListNode[] nodes = reverseList(before.next);
        ListNode front = nodes[0];
        ListNode tail = nodes[1];

        before.next = front;
        tail.next = after;

        return pre.next;

    }

    public ListNode[] reverseList(ListNode head){
        ListNode[] res = new ListNode[2];
        ListNode tail = head;
        res[1] = tail;

        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        res[0] = pre;
        return res;
    }
}
