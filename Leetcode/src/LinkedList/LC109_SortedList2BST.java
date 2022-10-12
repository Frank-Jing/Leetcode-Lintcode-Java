package Leetcode.src.LinkedList;

public class LC109_SortedList2BST {
    class Solution{
        public TreeNode sortedListToBST(ListNode head) {
            if(head == null) return null;
            if(head.next == null) return new TreeNode(head.val);
            //now the len of list at least >= 2
            int len = getListLen(head);
            ListNode beforeMid = getMidNode(head, len);
            ListNode mid = beforeMid.next;

            TreeNode top = new TreeNode(mid.val);

            beforeMid.next = null; //avoid loop
            top.left = sortedListToBST(head);

            top.right = sortedListToBST(mid.next);
            mid.next = null;//avoid loop

            return top;
        }

        public int getListLen(ListNode head){
            int i = 1;
            while(head.next != null){
                head = head.next;
                i++;
            }
            return i;
        }

        public ListNode getMidNode(ListNode head, int k){
            int mid = k >> 1;
            int i = 1;
            while(i++<mid){
                head = head.next;
            }
            return head;
        }
    }

    class Solution_2 {
        public TreeNode sortedListToBST(ListNode head) {
            if(head == null) return null;
            ListNode upMid = midOrUpMidPreNode(head);

            TreeNode root = null;
            if(upMid != null && upMid.next != null){
                root = new TreeNode(upMid.next.val);
                ListNode rightStart = upMid.next.next;
                //upMid.next.next = null;
                upMid.next = null;
                root.left = sortedListToBST(head);
                root.right = sortedListToBST(rightStart);

            }else if(upMid != null && upMid.next == null){
                root = new TreeNode(upMid.val);
            }

            return root;
        }

        public ListNode midOrUpMidPreNode(ListNode head){
            if (head == null || head.next == null) {
                return head; //not return null here
            }
            if (head.next.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }

}
