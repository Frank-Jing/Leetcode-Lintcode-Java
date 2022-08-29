package Leetcode.src;

public class LC19_RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(n == 1 && head.next == null){
            return null;
        }

        ListNode pre = new ListNode();
        ListNode cur = pre;
        pre.next = head;
        int count = 1;
        int len = getListLen(pre);

        while(count < len-n){
            cur = cur.next;
            count++;
        }
        cur.next = cur.next.next;

        return pre.next;
    }

    public int getListLen(ListNode head){
        int ans = 0;
        while(head != null){
            head = head.next;
            ans++;
        }
        return ans;
    }
}
