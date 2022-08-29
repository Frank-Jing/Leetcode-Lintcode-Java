package Leetcode.src;

public class LC24_SwpNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode pre = new ListNode(-1);
        ListNode res = pre;
        pre.next = head;
        ListNode cur = head;
        ListNode follower = head.next;

        while(follower != null){
            pre.next = follower;
            cur.next = follower.next;
            follower.next = cur;

            pre = cur;
            cur = cur.next;
            //This step ensure follower exist (i.e. we did not reach the end)
            if(cur == null) {
                break;
            }
            follower = cur.next;
        }

        return res.next;
    }
}
