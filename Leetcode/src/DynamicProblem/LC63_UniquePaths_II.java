package Leetcode.src.DynamicProblem;

public class LC63_UniquePaths_II {
    class Solution_dp {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;

            if(obstacleGrid[0][0] == 1 ||
                    obstacleGrid[m-1][n-1] == 1){
                return 0;
            }

            int[][] dp = new int[m][n];
            dp[m-1][n-1] = 1;

            for(int j = n-2; j>=0; j--){
                dp[m-1][j] = dp[m-1][j+1];
                if(obstacleGrid[m-1][j] == 1){
                    dp[m-1][j] =0;
                }
            }
            for(int i=m-2; i>=0; i--){
                dp[i][n-1] = dp[i+1][n-1];
                if(obstacleGrid[i][n-1] == 1){
                    dp[i][n-1] = 0;
                }
            }

            for(int i = m-2; i>=0; i--){
                for(int j = n-2; j>=0; j--){
                    dp[i][j] = obstacleGrid[i+1][j] == 1? 0 : dp[i+1][j];
                    dp[i][j] += obstacleGrid[i][j+1] == 1? 0 : dp[i][j+1];
                    if(obstacleGrid[i][j] == 1){
                        dp[i][j] = 0;
                    }
                }
            }

            return dp[0][0];
        }
    }
}
