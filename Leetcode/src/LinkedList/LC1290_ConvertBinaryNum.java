package Leetcode.src.LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC1290_ConvertBinaryNum {

    public int getDecimalValue(ListNode head) {
        if(head.next == null){
            return head.val;
        }

        int dec = 0;
        while(head != null){
            dec = dec*2 + head.val;
            head = head.next;
        }
        return dec;
    }

    public int getDecimalValue2(ListNode head) {
        if(head.next == null){
            return head.val;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        while(head != null){
            stack.push(head.val);
            head = head.next;
        }

        int bit = 0;
        int dec = 0;
        while(! stack.isEmpty()){
            int val = stack.pop();
            dec += Math.pow(2, bit++)*val;
        }
        return dec;
    }
}
