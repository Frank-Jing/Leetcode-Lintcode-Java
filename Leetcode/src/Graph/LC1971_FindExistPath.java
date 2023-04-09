package Leetcode.src.Graph;

import java.util.ArrayList;

public class LC1971_FindExistPath {

    // space complexity O(N^2)
    class Solution_MLE {
        boolean[] visit;
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            int[][] graph = new int[n][n];
            for(int[] edge : edges){
                graph[edge[0]][edge[1]] = 1;
                graph[edge[1]][edge[0]] = 1;
            }
            visit = new boolean[n];

            return dfs(source, destination, graph);
        }

        public boolean dfs(int cur, int dst, int[][] graph){
            if(cur == dst) return true;
            visit[cur] = true;

            boolean ans = false;

            for(int i = 0; i < graph[cur].length; i++){
                if(graph[cur][i] == 1 && visit[i] != true){
                    ans = ans || dfs(i, dst, graph);
                }
            }

            return ans;
        }
    }

    class Solution_DFS {
        boolean[] visit;
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            ArrayList<Integer>[] graph = new ArrayList[n];
            for(int i=0; i < n; i++) graph[i] = new ArrayList<>();
            for(int[] edge : edges){
                int a = edge[0], b = edge[1];
                graph[a].add(b);
                graph[b].add(a);
            }
            visit = new boolean[n];

            return dfs(source, destination, graph);
        }

        public boolean dfs(int cur, int dst, ArrayList<Integer>[] graph){
            if(cur == dst) return true;
            visit[cur] = true;

            boolean ans = false;

            for(int next : graph[cur]){
                if(visit[next] != true){
                    visit[next] = true;
                    ans = ans || dfs(next, dst, graph);
                }
            }

            return ans;
        }
    }

    class Solution_UnionFind {
        class UnionFind{
            int[] parent;
            public UnionFind(int n){
                parent = new int[n];
                for(int i =0; i< n; i++){
                    parent[i] = i;
                }
            }

            public int findFather(int x){
                while( x != parent[x]){
                    x = parent[x];
                }
                return x;
            }

            public void union(int x, int y){
                int a = findFather(x);
                int b = findFather(y);
                if(a == b) return;
                if(a > b){
                    parent[a] = b;
                }else{
                    parent[b] = a;
                }
            }
        }

        public boolean validPath(int n, int[][] edges, int source, int destination) {
            UnionFind uf = new UnionFind(n);
            for(int[] edge : edges){
                uf.union(edge[0], edge[1]);
            }

            return uf.findFather(source) == uf.findFather(destination);
        }
    }
}
