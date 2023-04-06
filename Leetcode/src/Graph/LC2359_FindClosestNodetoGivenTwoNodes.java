package Leetcode.src.Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC2359_FindClosestNodetoGivenTwoNodes {
    class Solution {
        public int closestMeetingNode(int[] edges, int node1, int node2) {
            int n = edges.length;
            int[] dist1 = dfs(node1, edges);
            int[] dist2 = dfs(node2, edges);

            int minDist = Integer.MAX_VALUE;
            int ans = -1;
            for(int i = 0; i < n; i++){
                if( minDist > Math.max(dist1[i], dist2[i])){
                    ans = i;
                    minDist = Math.min(minDist, Math.max(dist1[i], dist2[i]));
                }
            }
            return ans;
        }

        public int[] dfs(int node, int[] edges){
            int[] dist = new int[edges.length];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[node] = 0;
            Set<Integer> visited = new HashSet<>();
            int cur = node;
            int d = 0;
            while(edges[cur] != -1){
                visited.add(cur);
                cur = edges[cur];
                if(visited.contains(cur)) break;
                d++;
                dist[cur] =  d;
            }
            return dist;
        }
    }
}
