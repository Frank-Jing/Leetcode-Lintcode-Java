package Leetcode.src;

public class LC86_PatitionLinkedList {
    //divide linkedList into 2 part by benchmark x
    // <  >=
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;

        ListNode pre = new ListNode(-1); //node to get result, pin node
        ListNode less = new ListNode(-1);
        ListNode more = new ListNode(-1);
        ListNode mid = more; // pin node for < part
        ListNode start = less; // pin node for >= part

        while(head != null){
            if(head.val < x){
                less.next = head;
                less = less.next;
            }else{
                more.next = head;
                more = more.next;
            }
            ListNode next = head.next;
            head.next= null;
            head = next;
        }
        if(start.next != null){
            pre.next = start.next;
            less.next = mid.next;
        }else{
            pre.next = mid.next;
        }


        return pre.next;
    }

    //recursion method
    public ListNode partition2(ListNode head, int x) {
        if(head == null || head.next == null) return head;

        ListNode next = partition2(head.next, x);
        head.next = null;
        head = mergeListNode(head, next, x);

        return head;
    }

    public ListNode mergeListNode(ListNode pre, ListNode cur, int x){
        if(cur == null) return pre;
        //pre < x, cur < x no change
        //pre < x, cur >= x, no change
        // pre >=x, cur >= x, no change
        if(pre.val < x || (pre.val >= x && cur.val >= x)){
            pre.next = cur;
            return pre;
        }

        //pre >= x, cur < x, change
        ListNode ptr = cur;
        while(ptr != null){
            //if missing this step, >=x would not appear
            //as the corner case is 2 nodes to link
            if(ptr.val < x && ptr.next == null){
                ptr.next = pre;
                break;
            }
            if(ptr.next.val >= x){
                pre.next = ptr.next;
                ptr.next = pre;
                break;
            }
            ptr = ptr.next;
        }

        return cur;
    }
}
