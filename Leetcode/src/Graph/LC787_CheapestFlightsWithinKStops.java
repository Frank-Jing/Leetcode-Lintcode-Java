package Leetcode.src.Graph;

import java.util.*;

public class LC787_CheapestFlightsWithinKStops {
    class Solution_DFS_TLE {

        int fee = Integer.MAX_VALUE;
        boolean[] visit;
        int[][] dist;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // generate graph
            Map<Integer, Integer>[] adj = new HashMap[n];

            for(int i = 0; i < n; i++){
                adj[i] = new HashMap<Integer, Integer>();
            }
            for(int[] flight : flights){
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                adj[from].put(to, price);
            }

            visit = new boolean[n];
            visit[src] = true;

            dfs(src, dst, k, adj, 0);

            return fee == Integer.MAX_VALUE? -1 : fee;
        }

        public void dfs(int cur, int dst, int k, Map<Integer, Integer>[] adj, int priceSum){
            if(cur == dst){
                fee = priceSum;
                return;
            }

            if( k == -1) return;

            for(int next : adj[cur].keySet()){
                if(visit[next]) continue;
                if(priceSum + adj[cur].get(next) > fee) continue;
                visit[next] = true;
                dfs(next, dst, k-1, adj, priceSum + adj[cur].get(next));
                visit[next] = false;
            }
        }
    }
}
