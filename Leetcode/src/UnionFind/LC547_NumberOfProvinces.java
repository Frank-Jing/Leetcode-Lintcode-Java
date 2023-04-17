package Leetcode.src.UnionFind;

import java.util.Arrays;

public class LC547_NumberOfProvinces {
    class Solution_UnionFind {
        public int findCircleNum(int[][] isConnected) {
            if(isConnected.length == 1) return 1;
            int n = isConnected.length;
            UnionFind uf = new UnionFind(n);
            for(int i = 0; i < n; i++){
                for(int j = 0; j< n; j++){
                    if(isConnected[i][j] == 1) uf.union(i, j);
                }
            }

            return uf.set;
        }

        class UnionFind{
            int[] parent;
            int[] help;
            int set;

            public UnionFind(int n){
                parent = new int[n];
                help = new int[n];
                set = n;
                for(int i = 0; i<n; i++){
                    parent[i] = i;
                }
            }

            public int find(int x){
                int i =0;
                while(x != parent[x]){
                    help[i++] = x;
                    x = parent[x];
                }
                while(--i >=0){
                    parent[help[i]] = x;
                }

                return x;
            }

            public void union(int x, int y){
                int a = find(x);
                int b = find(y);

                if(a == b) return;
                if(a < b){
                    parent[b] = a;
                }else{
                    parent[a] = b;
                }
                set--;
            }
        }
    }

    class Solution_DFS {
        public int findCircleNum(int[][] isConnected) {
            if(isConnected.length == 1) return 1;
            int n = isConnected.length;
            boolean[] visit = new boolean[n];
            Arrays.fill(visit, false);
            int cnt =0;
            for(int i = 0; i<n; i++){
                if(isConnected[i][i] == 1 && !visit[i]){
                    cnt++;
                    dfs(isConnected, i, visit);
                }
            }

            return cnt;
        }

        public void dfs(int[][] isConnected, int i, boolean[] visit){
            visit[i] = true;
            for(int col  = 0; col < isConnected.length; col++){
                if(isConnected[i][col] == 1 && !visit[col]){
                    isConnected[col][col] = 0;
                    dfs(isConnected, col, visit);
                }
            }
        }
    }
}
