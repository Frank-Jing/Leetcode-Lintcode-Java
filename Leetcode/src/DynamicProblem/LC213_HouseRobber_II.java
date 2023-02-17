package Leetcode.src.DynamicProblem;

import java.util.Arrays;

public class LC213_HouseRobber_II {
    class Solution {
        public int rob(int[] nums) {
            int N = nums.length;
            if (N == 1) return nums[0];
            return Math.max(rob_help(Arrays.copyOfRange(nums, 0, N - 1)),
                    rob_help(Arrays.copyOfRange(nums, 1, N)));
        }

        //soln to House Robber with no loop
        public int rob_help(int[] nums) {
            int N = nums.length;
            if (N == 1) return nums[0];
            if (N == 2) return Math.max(nums[0], nums[1]);

            int[] dp = new int[N + 1];
            dp[0] = 0;
            dp[1] = nums[0];

            for (int i = 2; i < N + 1; i++) {
                dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
            }

            return dp[N];
        }
    }
}
