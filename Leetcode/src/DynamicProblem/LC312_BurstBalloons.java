package Leetcode.src.DynamicProblem;

public class LC312_BurstBalloons {
    class Solution_recursion {
        public int maxCoins(int[] nums) {
            int N = nums.length;
            int[] nums_new = new int[N + 2];
            nums_new[0] = 1;
            nums_new[N + 1] = 1;
            for (int i = 1; i < N + 1; i++) {
                nums_new[i] = nums[i - 1];
            }

            return process(nums_new, 1, N);
        }

        //return the max coins between index i and index j
        //while k is the last balloon to burst
        public int process(int[] nums, int i, int j) {
            if (i == j) return nums[i - 1] * nums[i] * nums[i + 1];

            int ans = 0;
            for (int k = i; k <= j; k++) {
                int gain = process(nums, i, k - 1) +
                        process(nums, k + 1, j) +
                        nums[i - 1] * nums[k] * nums[j + 1];
                ans = Math.max(ans, gain);
            }

            return ans;
        }
    }

    class Solution_dp {
        public int maxCoins(int[] nums) {
            int N = nums.length;
            if (nums.length == 1) return nums[0];

            int[] nums_new = new int[N + 2];
            nums_new[0] = 1;
            nums_new[N + 1] = 1;
            for (int i = 1; i < N + 1; i++) {
                nums_new[i] = nums[i - 1];
            }

            int[][] dp = new int[N + 2][N + 2];
            for (int i = 1; i <= N; i++) {
                dp[i][i] = nums_new[i - 1] * nums_new[i] * nums_new[i + 1];
            }

            for (int row = N - 1; row >= 1; row--) {
                for (int col = row + 1; col <= N; col++) {
                    int cnt = 0;
                    for (int k = row; k <= col; k++) {
                        int gain = dp[row][k - 1] + dp[k + 1][col] + nums_new[row - 1] * nums_new[k] * nums_new[col + 1];
                        cnt = Math.max(cnt, gain);
                    }
                    dp[row][col] = cnt;
                }
            }

            return dp[1][N];
        }
    }
}
