package Leetcode.src.UnionFind;

public class LC1254_NumberOfClosedIslands {
    class Solution_DFS {
        public int closedIsland(int[][] grid) {
            int cnt = 0;
            for(int i = 0; i< grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == 0){
                        if(dfs(grid, i, j)){
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
        }

        public boolean dfs(int[][] grid, int rowInd, int colInd){
            if(rowInd < 0 || rowInd >= grid.length || colInd < 0 || colInd >= grid[0].length) return false;
            if(grid[rowInd][colInd] == 1) return true;

            grid[rowInd][colInd] = 1;
            boolean ans = true;
            ans = dfs(grid, rowInd+1, colInd);
            ans &= dfs(grid, rowInd-1, colInd);
            ans &= dfs(grid, rowInd, colInd+1);
            ans &= dfs(grid, rowInd, colInd-1);

            return ans;
        }
    }
}
