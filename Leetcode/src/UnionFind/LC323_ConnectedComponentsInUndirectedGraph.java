package Leetcode.src.UnionFind;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC323_ConnectedComponentsInUndirectedGraph {
    class Solution {
        public int countComponents(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            for(int i = 0; i < edges.length; i++){
                int node1 = edges[i][0];
                int node2 = edges[i][1];
                uf.union(node1, node2);
                if(uf.sets == 1){
                    break;
                }
            }

            return uf.sets;
        }
    }

    class UnionFind {
        int[] parent;
        int sets;

        public UnionFind(int n){
            parent = new int[n];
            sets = n;
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        public int findFather(int i){
//            Deque<Integer> stack = new ArrayDeque<>();
            while(parent[i] != i){
//                stack.push(i);
                i = parent[i];
            }

//            while(!stack.isEmpty()){
//                int ind = stack.pop();
//                parent[ind] = i;
//            }
            return i;
        }

        public void union(int i, int j){
            int f1 = findFather(i);
            int f2 = findFather(j);
            if(f1 != f2){
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
