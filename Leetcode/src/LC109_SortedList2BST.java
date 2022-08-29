package Leetcode.src;

public class LC109_SortedList2BST {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.val);
        //now the len of list at least >= 2
        int len = getListLen(head);
        ListNode beforeMid = getMidNode(head, len);
        ListNode mid = beforeMid.next;

        TreeNode top = new TreeNode(mid.val);

        beforeMid.next = null;
        top.left = sortedListToBST(head);

        top.right = sortedListToBST(mid.next);
        mid.next = null;

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
