package Leetcode.src.SlidingWindow;

import java.util.LinkedList;

public class LC239_SlidingWindowMaximum {
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] maxArray = new int[n-k+1];
            LinkedList<Integer> qmax= new LinkedList<>(); //max deque
            int ind = 0;
            for(int R = 0; R < n; R++){
                while(!qmax.isEmpty() && nums[qmax.peekLast()] <= nums[R]){
                    qmax.pollLast();
                }
                qmax.addLast(R);
                if(qmax.peekFirst() == R - k){
                    qmax.pollFirst();
                }
                if(R >= k-1){
                    maxArray[ind++] = nums[qmax.peekFirst()];
                }
            }

            return maxArray;
        }
    }
}
