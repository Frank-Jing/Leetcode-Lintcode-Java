package Leetcode.src;

public class LC143_ReorderList {
    class Solution {
        public void reorderList(ListNode head) {
            if(head == null || head.next == null) return;
            ListNode mid = findMid(head);
            ListNode rightHalf = mid.next;
            mid.next = null;//avoid loop

            rightHalf = reverse(rightHalf);

            head = mergeTwoLists(head, rightHalf);
        }

        public ListNode findMid(ListNode head){
            ListNode fast = head, slow = head;
            //order of while loop condition is important for quick-slow method
            while(fast != null && fast.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        public ListNode reverse(ListNode head){
            if(head == null || head.next == null) return head;

            ListNode pre = null;
            ListNode next = null;
            while (head != null) {
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2){
            ListNode res = new ListNode(-1);
            ListNode cur = res;
            while(list2 != null){
                cur.next = list1;
                list1 = list1.next;
                cur = cur.next;
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;
            }

            cur.next = list1 == null? null : list1;

            return res.next;
        }


    }
}
