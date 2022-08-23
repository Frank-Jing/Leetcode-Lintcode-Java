package Leetcode.src;

public class LC02_AddTwoNumbers{
     //Definition for singly-linked list.
     public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int len1 = listSize(l1);
            int len2 = listSize(l2);
            ListNode l = len1 >= len2? l1:l2;

            ListNode currL = l;
            ListNode currS = l == l1? l2:l1;;

            int carry = 0;
            int curNum = 0;
            ListNode last = currL;

            while(currS != null){
                curNum = currL.val + currS.val + carry;
                currL.val = curNum%10;
                carry = curNum/10;
                last = currL;
                currL = currL.next;
                currS = currS.next;
            }

            while(currL != null){
                curNum = currL.val + carry;
                carry =  curNum/10;
                currL.val =  curNum%10;
                last = currL;
                currL = currL.next;
            }

            if(carry == 1){
                last.next = new ListNode(1);
            }

            return l;

        }

        public int listSize(ListNode l){
            int size = 0;
            while(l != null){
                size++;
                l = l.next;
            }
            return size;
        }
    }
}
