package Leetcode.src.LinkedList;

public class LC25_ReverseKGroupNode {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        int i = 0;
        ListNode cur = head;
        while(cur != null && i<k){
            cur = cur.next;
            i++;
        }

        if(i == k){
            cur = reverseKGroup(cur, k);
            while(i-->0){
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
            }
            head = cur;
        }

        return head;

    }
}
