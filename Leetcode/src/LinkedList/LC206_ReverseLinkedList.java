package Leetcode.src.LinkedList;

public class LC206_ReverseLinkedList {

    public static ListNode reverseLinkedList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return p;


    }
}
