package Leetcode.src.DynamicProblem;

public class LC931_MinimumFallingPathSum {
    class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int N = matrix.length;
            if (N == 1) return matrix[0][0];

            int[][] dp = new int[N][N];
            dp[N - 1] = matrix[N - 1];
            for (int row = N - 2; row >= 0; row--) {
                for (int col = 0; col < N; col++) {
                    if (col == 0) {
                        dp[row][col] = Math.min(dp[row + 1][col], dp[row + 1][col + 1]);
                    } else if (col == N - 1) {
                        dp[row][col] = Math.min(dp[row + 1][col - 1], dp[row + 1][col]);
                    } else {
                        dp[row][col] = Math.min(dp[row + 1][col - 1], Math.min(dp[row + 1][col], dp[row + 1][col + 1]));
                    }
                    dp[row][col] += matrix[row][col];
                }
            }

            int minNum = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                minNum = minNum > dp[0][i] ? dp[0][i] : minNum;
            }

            return minNum;
        }
    }
}
