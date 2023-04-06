package Leetcode.src.UnionFind;

import java.util.HashSet;
import java.util.Set;

public class LC694_NumberOFDistinctIsland {
    class Solution {
        public int numDistinctIslands(int[][] grid) {
            Set<String> set = new HashSet<>();
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[i].length; j++){
                    if(grid[i][j] == 1){
                        StringBuilder islandShape = new StringBuilder();
                        dfs(grid, i, j, islandShape, 'o');
                        if(!set.contains(islandShape.toString())){
                            set.add(islandShape.toString());
                        }
                    }
                }
            }
            return set.size();
        }

        public void dfs(int[][] grid, int i, int j, StringBuilder sb, char dir){
            if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0){
                return;
            }
            grid[i][j] = 0;
            sb.append(dir);
            dfs(grid, i-1, j, sb, 'u');
            dfs(grid, i+1, j, sb, 'd');
            dfs(grid, i, j-1, sb, 'l');
            dfs(grid, i, j+1, sb, 'r');
            //MUST HAVE, or there could be identical
            //[[1,1,0],[0,1,1],[0,0,0],[1,1,1],[0,1,0]]
            sb.append('e');

        }
    }
}
