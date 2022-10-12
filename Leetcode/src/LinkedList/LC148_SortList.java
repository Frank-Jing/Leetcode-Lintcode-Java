package Leetcode.src.LinkedList;

import Leetcode.src.LinkedList.ListNode;

public class LC148_SortList {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode mid = getMid(head);
        ListNode left = head, right = mid.next;
        mid.next = null; //avoid loop

        left = sortList(left);
        right = sortList(right);
        head = mergeTwoLists(left, right);

        return head;
    }

    public ListNode getMid(ListNode head){
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode fast = pre, slow = pre;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1 == null? list2 : list1;

        return res.next;
    }
}
