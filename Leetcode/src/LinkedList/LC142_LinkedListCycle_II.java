package Leetcode.src.LinkedList;

import java.util.HashSet;
import java.util.Set;

public class LC142_LinkedListCycle_II {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> map = new HashSet<>();
        while(head != null){
            if(map.contains(head)){
                return head;
            }
            map.add(head);
            head = head.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                fast = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }

        return null;
    }
}
