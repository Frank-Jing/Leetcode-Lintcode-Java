package Leetcode.src.Graph;

import java.util.*;

public class LC787_CheapestFlightsWithinKStops {

    // can't pass the case
    // n = 4, src = 0, dst = 3, k = 1
    // [[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
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

    class Solution_BFS {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // generate graph
            ArrayList<int[]>[] adj = generateGraph(n ,flights);

            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{src, 0});
            int stop = 0;

            while(!q.isEmpty()){
                int qsize = q.size();
                while( qsize-- > 0 ){
                    int[] cur = q.poll();
                    int curNode = cur[0], curCost = cur[1];

                    if(adj[curNode].size() == 0) continue;

                    for(int[] next : adj[curNode]){
                        int nextNode = next[0], nextPrice = next[1];
                        // Important, this would prune more branch
                        // some solution use int ans instead of int[] dist to prune
                        // ans case: this would prune when the current cost is already greater than ans
                        // dist: ensure every node, we have the cheapest cost
                        if(curCost + nextPrice >= dist[nextNode]) continue;
                        dist[nextNode] = curCost + nextPrice;
                        q.offer(new int[]{nextNode, dist[nextNode]});
                    }
                }
                if(stop++ >= k) break;
            }

            return dist[dst] == Integer.MAX_VALUE? -1 : dist[dst];
        }

        public ArrayList<int[]>[] generateGraph(int n, int[][] flights){
            ArrayList<int[]>[] adj = new ArrayList[n];
            for(int i = 0; i< n; i++){
                adj[i] = new ArrayList<>();
            }
            for(int[] flight : flights){
                int from = flight[0], to = flight[1], price = flight[2];
                adj[from].add(new int[]{to, price});
            }

            return adj;
        }
    }

    // similar to Dijikstra but the idea is BFS idea
    class Solution_BellmanFord {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            for(int i =0; i<= k; i++){
                int[] temp = Arrays.copyOf(dist, n);
                for(int[] flight : flights){
                    int from = flight[0], to = flight[1], price = flight[2];
                    if(dist[from] != Integer.MAX_VALUE){
                        temp[to] = Math.min(temp[to], dist[from] + price);
                    }
                }
                dist = temp;
            }

            return dist[dst] == Integer.MAX_VALUE? -1 : dist[dst];
        }
    }
}
