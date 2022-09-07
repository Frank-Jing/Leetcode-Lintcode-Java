package Leetcode.src.LinkedList;

public class LC369_NumberPlusOne {
    class Solution {
        public ListNode plusOne(ListNode head) {
            ListNode pre = new ListNode(0);
            pre.next= head;

            if(head.next == null){
                head.val = (head.val + 1)%10;
                if(head.val == 0){
                    pre.val = 1;
                    return pre;
                }else{
                    return pre.next;
                }
            }

            head = reverse(pre);
            ListNode cur = head;
            int dec = (cur.val + 1)/10;
            cur.val = (cur.val + 1)%10;
            while(dec != 0){
                cur.next.val += dec;
                cur = cur.next;
                dec = cur.val/10;
                cur.val = cur.val%10;
            }

            head = reverse(head);
            ListNode res = head.val == 0? head.next : head;
            return res;
        }

        public ListNode reverse(ListNode head){
            if(head == null || head.next == null) return head;

            ListNode pre = null;
            ListNode next = null;
            while(head != null){
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }
    }

    class Solution2 {
        public ListNode plusOne(ListNode head) {
            ListNode pre = new ListNode(0);
            pre.next = head;
            ListNode notNine = pre;

            while(head != null){
                if(head.val != 9){
                    notNine = head;
                }
                head = head.next;
            }

            notNine.val += 1;
            ListNode nine = notNine.next;
            while(nine != null){
                nine.val = 0;
                nine = nine.next;
            }

            return pre.val == 0? pre.next : pre;
        }
    }
}
