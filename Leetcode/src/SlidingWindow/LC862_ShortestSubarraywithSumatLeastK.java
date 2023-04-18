package Leetcode.src.SlidingWindow;

import java.util.LinkedList;

public class LC862_ShortestSubarraywithSumatLeastK {
    class Solution {
        public int shortestSubarray(int[] nums, int k) {

            int n = nums.length;
            long[] preSum = new long[n+1];
            for(int i = 1; i <= n; i++){
                preSum[i] = preSum[i-1] + (long)nums[i-1];
            }
            int len = Integer.MAX_VALUE;

            LinkedList<Integer> qmax = new LinkedList<>();
            for(int i = 0; i <= n; i++){
                while(!qmax.isEmpty() && preSum[qmax.peekLast()] >= preSum[i]){
                    qmax.pollLast();
                }
                while(!qmax.isEmpty() && preSum[qmax.peekFirst()] + k <= preSum[i]){
                    len = Math.min(len, i - qmax.pollFirst());
                }
                qmax.add(i);
            }

            return len == Integer.MAX_VALUE? -1 : len;

        }
    }
}
