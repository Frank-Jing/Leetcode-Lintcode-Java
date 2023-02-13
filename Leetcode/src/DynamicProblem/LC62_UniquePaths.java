package Leetcode.src.DynamicProblem;

public class LC62_UniquePaths {
    class _recursion {
        public int uniquePaths(int m, int n) {
            return process(0, 0, m, n);
        }

        public int process(int i, int j, int m, int n){
            if(i == m-1 && j == n-1) {
                return 1;
            }else if(i == m - 1){
                return process(i, j + 1, m, n);
            }else if(j == n-1){
                return process(i+1, j, m, n);
            }else{
                return process(i+1, j, m, n) + process(i, j+1, m, n);
            }
        }
    }

    class Solution_dp {
        public int uniquePaths(int m, int n) {

            int[][] dp = new int[m][n];
            dp[m-1][n-1] = 1;

            for(int j = n-1; j>=0; j--){
                dp[m-1][j] = 1;
            }
            for(int i=m-1; i>=0; i--){
                dp[i][n-1] = 1;
            }

            for(int i = m-2; i>=0; i--){
                for(int j = n-2; j>=0; j--){
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }

            return dp[0][0];
        }
    }
}
