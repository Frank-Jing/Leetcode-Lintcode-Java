package Leetcode.src.DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class LC994_RottingOrange {
    class Solution {
        int ROTTEN = 2;
        int FRESH = 1;
        int EMPTY = 0;
        int[][] directions = new int[4][2];


        public int orangesRotting(int[][] grid) {
            directions[0] = new int[]{-1, 0}; // left
            directions[1] = new int[]{0, 1}; //up
            directions[2] = new int[]{1, 0}; // right
            directions[3] = new int[]{0, -1}; //down

            int m = grid.length;
            int n = grid[0].length;

            Queue<int[]> q = new LinkedList<>();

            int orangeCnt = 0;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(grid[i][j] == ROTTEN){
                        q.offer(new int[]{i, j});
                    }
                    if(grid[i][j] != EMPTY){
                        orangeCnt++;
                    }
                }
            }

            int minCnt = 0;
            while(!q.isEmpty()){
                int size = q.size();
                orangeCnt -= size;
                for(int i = 0; i < size; i++){
                    int[] curPos = q.poll();
                    for(int[] direction : directions){
                        int col = curPos[1] + direction[0];
                        int row = curPos[0] + direction[1];
                        if(col >= 0 && col < n && row >= 0 && row < m && grid[row][col] == FRESH){
                            grid[row][col] = ROTTEN;
                            q.offer(new int[]{row, col});
                        }
                    }
                }
                minCnt++;
            }

            int ans = -1;
            if(orangeCnt == 0){
                ans = minCnt == 0? minCnt : minCnt - 1; //minCnt = 0 means no BFS started, q is empty at beginning
            }
            return ans;
        }
    }
}
