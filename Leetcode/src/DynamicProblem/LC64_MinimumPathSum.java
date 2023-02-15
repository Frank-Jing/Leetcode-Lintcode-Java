package Leetcode.src.DynamicProblem;

public class LC64_MinimumPathSum {
    class Solution_dp {
        public int minPathSum(int[][] grid) {
            int M = grid.length;
            int N = grid[0].length;

            //calc bottom line's sum
            for(int i = N - 1; i>=1; i--){
                grid[M-1][i-1] += grid[M-1][i];
            }

            //calc last col sum
            for(int j = M-1; j >= 1; j--){
                grid[j-1][N-1] += grid[j][N-1];
            }

            for(int i = M-1; i>= 1; i--){
                for(int j = N-1; j>=1; j--){
                    grid[i-1][j-1] += Math.min(grid[i][j-1], grid[i-1][j]);
                }
            }

            return grid[0][0];
        }
    }
}
