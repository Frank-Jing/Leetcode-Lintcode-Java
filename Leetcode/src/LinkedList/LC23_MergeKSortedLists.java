package Leetcode.src.LinkedList;

public class LC23_MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }else if(lists.length == 1){
            return lists[0];
        }

        ListNode res = process(lists, 0, lists.length - 1);

        return res;
    }

    public ListNode process(ListNode[] lists, int L, int R){
        if(L == R){
            return lists[L];
        }

        int mid = L + ((R-L)>>1);
        ListNode leftHead = process(lists, L, mid);
        ListNode rightHead = process(lists, mid+1, R);
        ListNode head = mergeTwoLists(leftHead, rightHead);

        return head;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
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
