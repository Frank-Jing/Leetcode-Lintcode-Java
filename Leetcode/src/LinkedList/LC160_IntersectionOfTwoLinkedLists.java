package Leetcode.src.LinkedList;

import java.util.HashSet;
import java.util.Set;

public class LC160_IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> nodeSeen = new HashSet<>();

        while(headA != null){
            nodeSeen.add(headA);
            headA = headA.next;
        }

        while(headB != null){
            if(nodeSeen.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while(pA != pB){
            pA = pA == null? headB : pA.next;
            pB = pB == null? headA : pB.next;
        }

        return pA;
    }
}
