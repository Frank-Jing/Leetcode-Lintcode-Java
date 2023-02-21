package Leetcode.src.DynamicProblem;

import java.util.Arrays;

public class LC543_Matrix01Distance {
    class Solution {
        public int[][] updateMatrix(int[][] mat) {
            int M = mat.length;
            int N = mat[0].length;
            int[][] dp = new int[M][N];
            for(int[] row: dp){
                Arrays.fill(row, M*N);
            }

            for(int i = 0; i <M; i++){
                for(int j = 0; j<N; j++){
                    if(mat[i][j] == 0){
                        dp[i][j] = 0;
                    }else{
                        if(i > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
                        if(j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + 1);
                    }
                }
            }

            for(int i = M-1; i>=0; i--){
                for(int j = N-1; j>=0; j--){
                    if (i < M - 1) dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    if (j < N - 1) dp[i][j] = Math.min(dp[i][j], dp[i][j+1] + 1);
                }
            }

            return dp;
        }
    }
}
