package Leetcode.src.DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class LC934_ShortestBridge {
    class Solution {
        Queue<int[]> islandQueue;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int shortestBridge(int[][] grid) {
            islandQueue = new LinkedList<>();

            here:
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == 1){
                        dfs(grid, i, j);
                        break here;
                    }
                }
            }

            int dist = 0;
            while(!islandQueue.isEmpty()){
                Queue<int[]> levelQueue = new LinkedList<>();
                for(int[] point : islandQueue){
                    int x = point[0];
                    int y = point[1];
                    for(int[] direction : directions){
                        int nextX = x + direction[0];
                        int nextY = y + direction[1];
                        if(0<= nextX && nextX < grid.length && 0<= nextY && nextY < grid[0].length){
                            if(grid[nextX][nextY] == 1){
                                return dist;
                            }else if(grid[nextX][nextY] == 0){
                                levelQueue.offer(new int[]{nextX, nextY});
                                grid[nextX][nextY] = -1;
                            }
                        }
                    }
                }
                dist++;
                islandQueue = levelQueue;
            }

            return dist;
        }

        public void dfs(int[][] grid, int x, int y){
            grid[x][y] = 2;
            islandQueue.offer(new int[]{x, y});

            for(int[] direction : directions){
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                if(0<= nextX && nextX < grid.length && 0<= nextY && nextY < grid[0].length && grid[nextX][nextY] == 1){
                    dfs(grid, nextX, nextY);
                }
            }
        }

    }
}
