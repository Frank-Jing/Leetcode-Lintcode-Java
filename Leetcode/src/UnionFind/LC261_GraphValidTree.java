package Leetcode.src.UnionFind;

public class LC261_GraphValidTree {
    class Solution_unionFind {
        public boolean validTree(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            for(int i = 0; i < edges.length; i++){
                int node1 = edges[i][0];
                int node2 = edges[i][1];
                uf.union(node1, node2);
            }

            return uf.sets == 1? uf.isTree: false;
        }
    }

    class UnionFind {
        int[] parent;
        boolean isTree;
        int sets;

        public UnionFind(int n){
            parent = new int[n];
            isTree = true;
            for(int i = 0; i < n; i++){
                parent[i] = i;
                sets++;
            }
        }

        public int findFather(int i){
            while(parent[i] != i){
                i = parent[i];
            }
            return i;
        }

        public void union(int i, int j){
            int f1 = findFather(i);
            int f2 = findFather(j);
            if(f1 == f2){
                isTree = false;
            }else{
                if(f1 < f2){
                    parent[f2] = f1;
                }else if(f1 > f2){
                    parent[f1] = f2;
                }
                sets--;
            }
        }
    }
}
