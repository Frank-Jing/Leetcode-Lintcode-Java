package Leetcode.src.LinkedList;

public class LC1721_SwapNodesInLinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode front = head, end = head, cur = head;
        //for front, when ind = k, mark
        //for end, when ind = k, start to jump with cur ptr
        //if required to swap nodes without swap value
        //get the nodes before front and end and do swap
        int ind = 1;
        while(cur != null){
            if(ind == k){
                front = cur;
            }
            if(ind > k){
                end = end.next;
            }
            cur = cur.next;
            ind++;
        }

        int tmp = front.val;
        front.val = end.val;
        end.val = tmp;

        return head;

    }
}
