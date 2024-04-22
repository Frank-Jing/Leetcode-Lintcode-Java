package Leetcode.src.Sorting;

import java.util.PriorityQueue;

public class LC23_MergeKSortedLists {
    /**
     * Definition for singly-linked list.

     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution_DivideAndConquer {
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

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution_heap {
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
            for(ListNode i : lists){
                if(i != null){
                    heap.offer(i);
                }
            }

            ListNode res = new ListNode(-1);
            ListNode cur = res;
            while(!heap.isEmpty()){
                ListNode top = heap.poll();
                cur.next = top;
                cur = cur.next;
                if(top.next != null){
                    heap.add(top.next);
                }
            }

            return res.next;
        }
    }
}
