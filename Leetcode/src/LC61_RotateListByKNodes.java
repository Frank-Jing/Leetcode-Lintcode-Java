package Leetcode.src;

public class LC61_RotateListByKNodes {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0){
            return head;
        }

        int len = getLen(head);
        k = k%len;
        if(k == 0){
            return head;
        }

        ListNode mid = getLastNode(head, len-k);
        ListNode tail = getLastNode(mid.next, k);

        tail.next = head;
        head = mid.next;
        mid.next = null;

        return head;
    }

    public Integer getLen(ListNode head){
        int cnt = 1;
        while(head.next != null){
            head = head.next;
            cnt++;
        }
        return cnt;
    }

    public ListNode getLastNode(ListNode head, int n){
        int i = 1;
        while(i<n){
            head = head.next;
            i++;
        }
        return head;
    }
}
