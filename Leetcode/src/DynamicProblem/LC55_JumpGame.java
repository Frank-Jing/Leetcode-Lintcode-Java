package Leetcode.src.DynamicProblem;

public class LC55_JumpGame {
    class Solution {
        public boolean canJump(int[] nums) {
            int N = nums.length;
            boolean[] dp = new boolean[N];
            dp[N - 1] = true;

            for (int i = nums.length - 2; i >= 0; i--) {
                if (i + nums[i] >= N - 1) {
                    dp[i] = true;
                } else if (nums[i] == 0) {
                    dp[i] = false;
                } else {
                    boolean reach = false;
                    for (int j = 1; j <= nums[i]; j++) {
                        if (dp[i + j] == true) {
                            reach = true;
                            break;
                        }
                    }
                    dp[i] = reach;
                }
            }

            return dp[0];
        }
    }
}
