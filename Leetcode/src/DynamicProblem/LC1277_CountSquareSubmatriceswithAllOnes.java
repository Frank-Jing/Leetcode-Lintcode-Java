package Leetcode.src.DynamicProblem;

public class LC1277_CountSquareSubmatriceswithAllOnes {
    class Solution {
        public int countSquares(int[][] matrix) {
            int cnt = 0;

            int M = matrix.length;
            int N = matrix[0].length;

            int[][] dp = new int[2][N];
            for (int j = 0; j < N; j++) {
                dp[0][j] = matrix[0][j] == 1 ? 1 : 0;
                cnt += dp[0][j];
            }

            for (int i = 1; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (j == 0) {
                        dp[1][0] = matrix[i][0] == 1 ? 1 : 0;
                    } else {
                        if (matrix[i][j] == 1) {
                            dp[1][j] = Math.min(Math.min(dp[1][j - 1], dp[0][j]), dp[0][j - 1]) + 1;
                        } else {
                            dp[1][j] = 0;
                        }
                    }
                    cnt += dp[1][j];
                }
                int[] temp = new int[N];
                dp[0] = dp[1];
                dp[1] = temp;
            }

            return cnt;
        }
    }
}
