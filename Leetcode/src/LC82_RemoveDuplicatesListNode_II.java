package Leetcode.src;

public class LC82_RemoveDuplicatesListNode_II {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode pre = new ListNode(-1);
        pre.next = head;

        ListNode fix = pre;
        ListNode move = pre;
        while(fix.next!= null){
            if(fix.next.next == null){
                break;
            }

            if(fix.next.val == fix.next.next.val){
                int duplicateVal = fix.next.val;
                move = fix.next;
                while(move!= null){
                    move = move.next;
                    if(move == null || move.val != duplicateVal){
                        break;
                    }
                }
                fix.next = move;
                move = fix;
            }else{
                fix = fix.next;
                move = fix;
            }
        }
        return pre.next;
    }
}
