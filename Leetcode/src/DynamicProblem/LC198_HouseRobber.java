package Leetcode.src.DynamicProblem;

public class LC198_HouseRobber {
    class Solution_recursion {
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return Math.max(nums[0], nums[1]);

            return process(nums, nums.length);
        }

        public int process(int[] nums, int i) {
            if (i <= 0) return 0;

            int ans = Math.max(process(nums, i - 1), nums[i - 1] + process(nums, i - 2));
            return ans;
        }
    }

    class Solution_dp {
        public int rob(int[] nums) {
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
