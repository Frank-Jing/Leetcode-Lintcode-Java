package Leetcode.src.LinkedList;

public class LC203_RemoveLinkedListNode {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;

        ListNode res = new ListNode(-1);
        ListNode pre = res;
        while(head.next != null){
            if(head.val != val){
                pre.next = head;
                pre = pre.next;
            }
            head = head.next;
        }

        pre.next = head.val == val? null : head;

        return res.next;
    }
}
