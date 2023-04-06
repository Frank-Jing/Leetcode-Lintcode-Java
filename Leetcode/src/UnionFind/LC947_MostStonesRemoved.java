package Leetcode.src.UnionFind;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC947_MostStonesRemoved {
    class Solution_wrong {
        public int removeStones(int[][] stones) {
            if(stones.length == 1) return 0;
            int remove = 0;
            Set<Integer> rowOccupied = new HashSet<>();
            Set<Integer> colOccupied = new HashSet<>();

            // can't handle the case that when isolated points are connected by the stones later
            // for example
            // [0,0], [1,1] remove now is 0
            // another [0,1], remove would be 2 but the output only remove 1 on [0,1]
            for(int[] cord : stones){
                int x = cord[0];
                int y = cord[1];

                // new point
                if(!rowOccupied.contains(x) && !colOccupied.contains(y)){
                    rowOccupied.add(x);
                    colOccupied.add(y);
                    continue;
                }

                //point can remove
                if(!rowOccupied.contains(x)){
                    rowOccupied.add(x);
                }
                if(!colOccupied.contains(y)){
                    colOccupied.add(y);
                }
                if(rowOccupied.contains(x)){
                    remove++;
                    continue;
                }else if(colOccupied.contains(y)){
                    remove++;
                }
            }

            return remove;
        }
    }

    class Solution_unionFind {
        class UnionFind{
            int size;
            int[] parent;
            int[] rank;

            public UnionFind(int n){
                this.size = n;
                this.parent = new int[n];
                this.rank = new int[n];

                for(int i = 0; i< n; i++){
                    parent[i] = i;
                    rank[i] = 1;
                }
            }

            public int findParent(int i){
                Stack<Integer> help = new Stack<>();
                while(parent[i] != i){
                    i = parent[i];
                    help.push(i);
                }
                while(!help.isEmpty()){
                    parent[help.pop()] = i;
                }
                return i;

            }
            public void union(int i, int j){
                int a = findParent(i);
                int b = findParent(j);
                if(a == b){
                    return;
                }else{
                    if(rank[a] >= rank[b]){
                        rank[a] += rank[b];
                        parent[b] = a;
                    }else{
                        rank[b] += rank[a];
                        parent[a] = b;
                    }
                    this.size--;
                }
                return;
            }
        }

        public boolean isSameRowOrCol(int[][]stones, int i, int j){
            int[] stone1 = stones[i];
            int[] stone2 = stones[j];
            return stone1[0] == stone2[0] || stone1[1] == stone2[1];

        }

        public int removeStones(int[][] stones) {
            int n = stones.length;
            if(n <= 1) return 0;

            UnionFind uf = new UnionFind(n);
            for(int i = 0; i < n; i++){
                for(int j = i+1; j<n; j++){
                    if(isSameRowOrCol(stones, i, j)){
                        uf.union(i, j);
                    }
                }
            }

            return n - uf.size;
        }
    }
}
