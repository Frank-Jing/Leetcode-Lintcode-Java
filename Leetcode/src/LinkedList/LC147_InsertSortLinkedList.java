package Leetcode.src.LinkedList;

public class LC147_InsertSortLinkedList {
    class Solution {
        public ListNode insertionSortList(ListNode head) {
            if(head == null || head.next == null) return head;

            ListNode cur = head.next;
            int ind = 2;
            while(cur != null){
                ListNode roundStart = head;
                int end = 1;
                while(end < ind){
                    if(roundStart.val > cur.val){
                        swapNodeValues(roundStart, cur);
                    }
                    end++;
                    roundStart = roundStart.next;
                }
                cur = cur.next;
                ind++;
            }
            return head;
        }

        public void swapNodeValues(ListNode node1, ListNode node2){
            int tmp = node2.val;
            node2.val = node1.val;
            node1.val = tmp;
        }
    }

    class Solution2 {
        public ListNode insertionSortList(ListNode head) {
            if(head == null || head.next == null) return head;

            ListNode pre = new ListNode();
            while(head != null){
                addNode(pre, head);
                head = head.next;
            }
            return pre.next;
        }

        public void addNode(ListNode pre, ListNode node){
            while(pre.next != null && pre.next.val < node.val){
                pre = pre.next;
            }
            ListNode add = new ListNode(node.val);
            add.next = pre.next;
            pre.next = add;
        }
    }

    class Solution3 {
        public ListNode insertionSortList(ListNode head) {
            if(head == null || head.next == null) return head;

            ListNode dummyHead = new ListNode();
            ListNode cur = head;

            while(cur != null){
                ListNode pre = dummyHead; //initial start of each cycle

                while(pre.next != null && pre.next.val < cur.val){
                    pre = pre.next;
                }

                ListNode next = cur.next; //ensure the pointer to next node
                //insert node
                cur.next = pre.next;
                pre.next = cur;

                cur = next;
            }

            return dummyHead.next;
        }
    }
}
