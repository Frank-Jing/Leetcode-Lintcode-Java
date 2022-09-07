package Leetcode.src.LinkedList;

public class LC24_SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode pre = new ListNode(-1);
        ListNode res = pre;
        pre.next = head;
        ListNode cur = head;
        ListNode last = head.next;

        while(last != null){
            pre.next = last;
            cur.next = last.next;
            last.next = cur;

            pre = cur;
            cur = cur.next;
            if(cur == null){
                break;
            }
            last = cur.next;
        }

        return res.next;
    }
    //recursive
    public ListNode swapPairs2(ListNode head) {

        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        firstNode.next  = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }
}
