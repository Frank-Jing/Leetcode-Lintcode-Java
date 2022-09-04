package Leetcode.src;

public class LC237_DeleteLinkedListNode_II {
    public void deleteNode(ListNode node) {
        //4->1->5->3 remove 1
        //4->1  5->3
        //4->5 5(remove)->3
        //4->5->3
        ListNode next = node.next;
        ListNode connect = next.next;
        node.val = next.val;
        node.next = connect;
        next.next = null;

        return;
    }
}
