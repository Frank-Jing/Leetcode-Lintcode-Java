package Leetcode.src.Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class LC1466_ReorderRoutesMakeAllPathsToCapital {
    class Solution_DFS {

        boolean[] toCapital;
        int cnt;

        public int minReorder(int n, int[][] connections) {
            ArrayList<Integer>[] out = new ArrayList[n];
            ArrayList<Integer>[] in = new ArrayList[n];
            for(int i = 0; i<n; i++){
                out[i] = new ArrayList<Integer>();
                in[i] = new ArrayList<Integer>();
            }
            for(int[] road : connections){
                int from = road[0], to = road[1];
                out[from].add(to);
                in[to].add(from);
            }

            toCapital = new boolean[n];
            Arrays.fill(toCapital, false);
            toCapital[0] = true;
            cnt = 0;

            dfs(0, in, out);
            return cnt;
        }

        public void dfs(int cur, ArrayList<Integer>[] in, ArrayList<Integer>[] out){
            // the whole dfs starts from capital 0
            // all direct routes to capital is marked as true;
            for(int from : in[cur]){
                if(toCapital[from] == true) continue; //prune branch
                toCapital[from] = true;
                dfs(from, in, out);
            }

            // toCapital[cur] is always true from here
            for(int to : out[cur]){
                // IMPORTANT to avoid stack overflow
                // if the cur -> to already is linked to capital, avoid traverse again
                if(toCapital[to] == true) continue;
                cnt++;
                toCapital[to] = true;
                dfs(to, in, out);
            }
        }
    }

    class Solution_DFS_faster {
        int count = 0;

        public void dfs(int node, int parent, ArrayList<int[]>[] adj) {
            if (adj[node].size() == 0) {
                return;
            }
            for (int[] nei : adj[node]) {
                int next = nei[0];
                int sign = nei[1];
                if (next != parent) {
                    count += sign;
                    dfs(next, node, adj);
                }
            }
        }

        public int minReorder(int n, int[][] connections) {
            ArrayList<int[]>[] adj = new ArrayList[n];
            for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
            for (int[] connection : connections) {
                int from = connection[0], to = connection[1];
                adj[from].add(new int[]{to, 1}); // [to, direction] //direction 1 means outpath
                adj[to].add(new int[]{from, 0}); // [from, direction] //direction 0 means inpath
            }
            dfs(0, -1, adj);
            return count;
        }
    }
}
