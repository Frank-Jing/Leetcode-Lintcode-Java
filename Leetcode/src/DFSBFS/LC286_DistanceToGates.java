package Leetcode.src.DFSBFS;

import java.util.LinkedList;
import java.util.Queue;

public class LC286_DistanceToGates {
    class Solution {

        int inf = Integer.MAX_VALUE;
        int wall = -1;
        int gate = 0;
        int[][] directions = new int[4][2];

        public void wallsAndGates(int[][] rooms) {
            directions[0] = new int[]{-1, 0}; // left
            directions[1] = new int[]{0, 1}; //up
            directions[2] = new int[]{1, 0}; // right
            directions[3] = new int[]{0, -1}; //down

            int m = rooms.length;
            int n = rooms[0].length;
            Queue<int[]> q = new LinkedList<>();

            // find gate
            for(int row = 0; row<m; row++){
                for(int col = 0; col<n; col++){
                    if(rooms[row][col] == gate){
                        q.offer(new int[]{col, row});
                    }
                }
            }

            // BFS
            int dist = 1;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i++){
                    int[] pos = q.poll();
                    for(int[] direction : directions){
                        int dx = direction[0];
                        int dy = direction[1];
                        int x = pos[0] + dx;
                        int y = pos[1] + dy;
                        if(x >= 0 && x < n && y >= 0 && y < m && rooms[y][x] == inf){
                            rooms[y][x] = dist;
                            q.offer(new int[]{x, y});
                        }
                    }
                }
                dist++;
            }
        }
    }
}
