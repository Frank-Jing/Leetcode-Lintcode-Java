package Leetcode.src.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC1129_ShortestPathAlternatingColors {
    class Solution_DFS {
        public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
            ArrayList<int[]>[] adj = generateGraph(n, redEdges, blueEdges);
            boolean[][] visit = new boolean[n][2]; // fopr every node, 0 for red, 1 for blue
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            ans[0] = 0;

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 0, -1}); // start = 0; step = 0. color red/blue random as -1
            visit[0][0] = visit[0][1] = true;

            while(queue.size() > 0){
                int[] cur = queue.poll();
                int curNode = cur[0], curStep = cur[1], curColor = cur[2];

                if(adj[curNode].size() == 0) continue;

                for(int[] next : adj[curNode]){
                    int nextNode= next[0], nextColor = next[1];
                    if(visit[nextNode][nextColor] || nextColor == curColor) continue;

                    if(ans[nextNode] == -1) ans[nextNode] = 1+curStep;
                    visit[nextNode][nextColor] = true;
                    queue.add(new int[]{nextNode, 1+curStep, nextColor});
                }
            }

            return ans;
        }

        public ArrayList<int[]>[] generateGraph(int n, int[][] redEdges, int[][] blueEdges){
            ArrayList<int[]>[] adj = new ArrayList[n];
            for(int i = 0; i< n; i++){
                adj[i] = new ArrayList<int[]>();
            }
            for(int[] edge : redEdges){
                int from = edge[0];
                int to = edge[1];
                adj[from].add(new int[]{to, 0});
            }
            for(int[] edge : blueEdges){
                int from = edge[0];
                int to = edge[1];
                adj[from].add(new int[]{to, 1});
            }
            return adj;
        }
    }
}
