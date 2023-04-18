package Leetcode.src.SlidingWindow;

import java.util.LinkedList;

// similar to LC862
public class LC209_MinimumSizeSubarraySum {
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length;
            long[] preSum = new long[n+1];
            for(int i = 1; i <= n; i++){
                preSum[i] = preSum[i-1] + (long)nums[i-1];
            }
            int len = Integer.MAX_VALUE;

            LinkedList<Integer> qmax = new LinkedList<>();
            for(int i = 0; i <= n; i++){
                while(!qmax.isEmpty() && preSum[qmax.peekFirst()] + target <= preSum[i]){
                    len = Math.min(len, i - qmax.pollFirst());
                }
                qmax.add(i);
            }

            return len == Integer.MAX_VALUE? 0 : len;
        }
    }
}
