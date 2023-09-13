package Leetcode.src.DynamicProblem;

public class LC53_MaximumSubarray {
    class Solution_slidingWindow {
        public int maxSubArray(int[] nums) {
            int left = 0, right = 0;
            int maxSum = Integer.MIN_VALUE;

            int windowSum = 0;
            for (int i = 0; i < nums.length; i++) {
                windowSum += nums[i];
                maxSum = Math.max(maxSum, windowSum);
                right++;

                while (windowSum < 0) {
                    windowSum -= nums[left];
                    left++;
                }
            }
            return maxSum;
        }
    }


    class Solution_presum {
        //WRONG Solution
        public int maxSubArray(int[] nums) {
            int N = nums.length;
            int[] presum = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                presum[i] = presum[i - 1] + nums[i - 1];
            }

            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int i = 0; i <= N; i++) {
                max = Math.max(presum[i], max);
                min = Math.min(presum[i], min);
                // here we can't ensure that peak position is after bottom;
                // a counter example is [-1]
            }

            return max - min;
        }

        public int maxSubArray_fixed(int[] nums) {
            int N = nums.length;
            int[] presum = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                presum[i] = presum[i - 1] + nums[i - 1];
            }

            int ans = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                min = Math.min(presum[i], min); // ensure min is the minimum from presum[0,i]
                ans = Math.max(ans, presum[i + 1] - min);
                // get max subarray [j, i] using presum[i+1] - presum[j]
            }

            return ans;
        }
    }

    class Solution_dp {
        public int maxSubArray(int[] nums) {
            int N = nums.length;
            int[] dp = new int[N];
            dp[0] = nums[0];

            int max = dp[0];
            for(int i = 1; i< N; i++){
                // dp[i] means the maxSubArray ended with nums[i]
                // so dp[i+1] has 2 scenarios:
                // 1. dp[i] > 0, then nums[i+1] + dp[i] forms a new subarray ended with num[i+1]
                // 2. ignore previous dp[i-1] since it's negative value;
                dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
                max = Math.max(dp[i], max);
            }

            return max;
        }
    }
}
