package Leetcode.src.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class LC234_PalindromeLinkedList {
    class Solution {
        ListNode ptr;

        public boolean isPalindrome(ListNode head) {
            ptr = head;
            return recursiveCheck(head);
        }

        public boolean recursiveCheck(ListNode node){
            if(node == null) return true;
            //here we must not use node.next == null, as last point would be compared with ptr = head

            if(!recursiveCheck(node.next)) return false;
            if(ptr.val != node.val) return false;
            ptr = ptr.next;

            return true;
        }
    }

    class Solution2 {
        public boolean isPalindrome(ListNode head) {
            if(head == null || head.next == null) return true;
            List<Integer> arr = new ArrayList<>();
            while(head != null){
                arr.add(head.val);
                head = head.next;
            }

            int N = arr.size();
            for(int i =0; i<N/2; i++){
                if(arr.get(i) != arr.get(N-i-1)){
                    return false;
                }
            }

            return true;

        }
    }

    class Solution3 {
        public boolean isPalindrome(ListNode head) {
            if(head == null || head.next == null) return true;
            boolean ans = true;

            ListNode tail = reverse(copy(head));
            while(head != null){
                if(head.val != tail.val){
                    ans = false;
                    break;
                }
                head = head.next;
                tail = tail.next;
            }

            return ans;
        }

        public ListNode reverse(ListNode head){
            ListNode cur = head;
            ListNode pre = null;
            while(cur != null){
                ListNode temp = cur;
                cur = cur.next;
                temp.next = pre;
                pre = temp;
            }

            return pre;
        }

        public ListNode copy(ListNode head){
            ListNode pre = new ListNode(-1);
            ListNode copy = new ListNode(head.val);
            pre.next = copy;
            while(head.next != null){
                head = head.next;
                copy.next = new ListNode(head.val);
                copy = copy.next;
            }
            return pre.next;
        }
    }

    // O(1) space complexity
    class Solution_SpaceEfficient {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            ListNode n1 = head;
            ListNode n2 = head;
            while (n2.next != null && n2.next.next != null) { // find mid node
                n1 = n1.next; // n1 -> mid
                n2 = n2.next.next; // n2 -> end
            }
            // n1 中点

            n2 = n1.next; // n2 -> right part first node
            n1.next = null; // mid.next -> null
            ListNode n3 = null;
            while (n2 != null) { // right part convert
                n3 = n2.next; // n3 -> save next node
                n2.next = n1; // next of right node convert
                n1 = n2; // n1 move
                n2 = n3; // n2 move
            }
            n3 = n1; // n3 -> save last node
            n2 = head;// n2 -> left first node
            boolean res = true;
            while (n1 != null && n2 != null) { // check palindrome
                if (n1.val!= n2.val) {
                    res = false;
                    break;
                }
                n1 = n1.next; // left to mid
                n2 = n2.next; // right to mid
            }
            // n1 = n3.next;
            // n3.next = null;
            // while (n1 != null) { // recover list
            // 	n2 = n1.next;
            // 	n1.next = n3;
            // 	n3 = n1;
            // 	n1 = n2;
            // }
            return res;
        }
    }
}
