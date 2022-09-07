package Leetcode.src.LinkedList;

public class LC141_LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode fast = head.next.next, slow = head.next;
        while(fast != slow){
            if(fast == null || fast.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        return true;
    }
}
