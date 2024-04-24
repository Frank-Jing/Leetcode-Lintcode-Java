package Leetcode.src.DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class LC1091_ShortestPathBinaryMatrix {
    class Solution {
        int wall = -1;
        int[][] directions = new int[8][2];

        public int shortestPathBinaryMatrix(int[][] grid) {
            if(grid[0][0] == 1) return -1;

            directions[0] = new int[]{0, -1}; // dy, dx, left
            directions[1] = new int[]{-1, -1}; // top-left
            directions[2] = new int[]{-1, 0}; // top
            directions[3] = new int[]{-1, 1}; // top-right
            directions[4] = new int[]{0, 1}; //right
            directions[5] = new int[]{1, 1}; // down-right;
            directions[6] = new int[]{1, 0}; //down
            directions[7] = new int[]{1, -1}; //down-left

            int m = grid.length;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < m; j++){
                    if(grid[i][j] == 1){
                        grid[i][j] = wall;
                    }
                }
            }

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
            int cnt = 1;

            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i< size; i++){
                    int[] pos = q.poll();
                    if(pos[0] == m-1 && pos[1] == m-1) return cnt;
                    for(int[] direction : directions){
                        int dx = direction[1];
                        int dy = direction[0];
                        int x = pos[1] + dx;
                        int y = pos[0] + dy;
                        if(x >= 0 && x < m && y >= 0 && y < m && grid[y][x] == 0){
                            grid[y][x] = wall;
                            q.offer(new int[]{y, x});
                        }
                    }
                }
                cnt++;
            }

            return -1;

        }
    }
}
