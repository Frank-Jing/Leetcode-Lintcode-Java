package Leetcode.src.DynamicProblem;

public class LC221_MaximalSquare {
    class Solution_dp {
        public int maximalSquare_dp(char[][] matrix) {
            int max = 0;

            int M = matrix.length;
            int N = matrix[0].length;

            int[][] dp = new int[M][N];
            for (int i = 0; i < M; i++) {
                if (matrix[i][0] == '1') {
                    dp[i][0] = 1;
                    max = 1;
                }
            }
            for (int j = 0; j < N; j++) {
                if (matrix[0][j] == '1') {
                    dp[0][j] = 1;
                    max = 1;
                }
            }

            for (int i = 1; i < M; i++) {
                for (int j = 1; j < N; j++) {
                    if (matrix[i][j] == '0') {
                        dp[i][j] = 0;
                    } else {
                        if (dp[i][j - 1] != 0 && dp[i - 1][j] != 0 && dp[i - 1][j - 1] != 0) {
                            float square = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                            dp[i][j] = (int) Math.pow(Math.sqrt(square) + 1, 2);
                        } else {
                            dp[i][j] = 1;
                        }
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }

            return max;
        }
    }


    class Solution_dp_2row {
        public int maximalSquare(char[][] matrix) {
            int max = 0;

            int M = matrix.length;
            int N = matrix[0].length;

            int[][] dp = new int[2][N];
            for (int j = 0; j < N; j++) {
                if (matrix[0][j] == '1') {
                    dp[0][j] = 1;
                    max = 1;
                }
            }

            for (int i = 1; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (j == 0) {
                        dp[1][0] = matrix[i][0] == '1' ? 1 : 0;
                    } else {
                        if (matrix[i][j] == '0') {
                            dp[1][j] = 0;
                        } else {
                            if (dp[1][j - 1] != 0 && dp[0][j] != 0 && dp[0][j - 1] != 0) {
                                float square = Math.min(Math.min(dp[1][j - 1], dp[0][j]), dp[0][j - 1]);
                                dp[1][j] = (int) Math.pow(Math.sqrt(square) + 1, 2);
                            } else {
                                dp[1][j] = 1;
                            }
                        }
                    }

                    max = Math.max(max, dp[1][j]);
                }
                int[] temp = new int[N];
                dp[0] = dp[1];
                dp[1] = temp;
            }

            return max;
        }
    }

    // DO NOT USE Math.pow or Math.sqrt and conversion of float to int
    // SLOW DOWN the speed significantly
    class Solution {
        public int maximalSquare(char[][] matrix) {
            int max = 0;

            int M = matrix.length;
            int N = matrix[0].length;

            int[][] dp = new int[2][N];
            for (int j = 0; j < N; j++) {
                dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
                max = Math.max(max, dp[0][j]);
            }

            for (int i = 1; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (j == 0) {
                        dp[1][0] = matrix[i][0] == '1' ? 1 : 0;
                    } else {
                        if (matrix[i][j] == '1') {
                            dp[1][j] = Math.min(Math.min(dp[1][j - 1], dp[0][j]), dp[0][j - 1]) + 1;
                        } else {
                            dp[1][j] = 0;
                        }
                    }
                    max = Math.max(max, dp[1][j]);
                }
                int[] temp = new int[N];
                dp[0] = dp[1];
                dp[1] = temp;
            }

            return max * max;
        }
    }
}
