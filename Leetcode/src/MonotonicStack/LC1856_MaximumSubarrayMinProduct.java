package Leetcode.src.MonotonicStack;

import java.util.Stack;

public class LC1856_MaximumSubarrayMinProduct {
    class Solution_II {
        final int mod = 1000000007;
        public int maxSumMinProduct(int[] arr) {
            int size = arr.length;
            long[] sums = new long[size];
            sums[0] = arr[0];
            for (int i = 1; i < size; i++) {
                sums[i] = sums[i - 1] + arr[i];
            }
            long max = Long.MIN_VALUE;
            int[] stack = new int[size];
            int stackSize = 0;
            for (int i = 0; i < size; i++) {
                while (stackSize != 0 && arr[stack[stackSize - 1]] >= arr[i]) {
                    int j = stack[--stackSize];
                    max = Math.max(max,
                            (stackSize == 0 ? sums[i - 1] : (sums[i - 1] - sums[stack[stackSize - 1]])) * arr[j]);
                }
                stack[stackSize++] = i;
            }
            while (stackSize != 0) {
                int j = stack[--stackSize];
                max = Math.max(max,
                        (stackSize == 0 ? sums[size - 1] : (sums[size - 1] - sums[stack[stackSize - 1]])) * arr[j]);
            }
            return (int) (max % mod);
        }
    }

    class Solution_I {
        final int mod = 1000000007;
        public int maxSumMinProduct(int[] nums) {
            int n = nums.length;
            long[] preSum = new long[n];
            preSum[0] = nums[0];
            for(int i = 1; i<n; i++){
                preSum[i] = preSum[i-1] + nums[i];
            }

            long ans = Long.MIN_VALUE;
            Stack<Integer> stack = new Stack<>();
            for(int i =0; i< n; i++){
                while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]){
                    int j = stack.pop();
                    ans = Math.max(ans, (stack.isEmpty()? preSum[i-1] : preSum[i-1] - preSum[stack.peek()])*nums[j]);
                }
                stack.push(i);
            }

            while(!stack.isEmpty()){
                int j = stack.pop();
                ans = Math.max(ans, (stack.isEmpty()? preSum[n-1] : preSum[n-1] - preSum[stack.peek()])*nums[j]);
            }
            return (int)(ans%mod);
        }
    }
}
