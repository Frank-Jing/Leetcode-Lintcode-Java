package Leetcode.src.UnionFind;

import java.util.LinkedList;
import java.util.List;

public class LC305_NumberOfIslands_II {
    class Solution {
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            List<Integer> ans = new LinkedList<>();
            UnionFind uf = new UnionFind(m, n);
            for(int[] pos : positions){
                int x = pos[0];
                int y = pos[1];
                ans.add(uf.add(x, y));
            }

            return ans;
        }
    }

    class UnionFind {
        int[] parent;
        boolean[] visited;
        int setCnt;
        int row;
        int col;

        public UnionFind (int m, int n){
            this.row = m;
            this.col = n;
            int len = m*n;
            parent = new int[len];
            visited = new boolean[len];
        }

        public int findFather(int ind){
            // Deque<Integer> stack = new ArrayDeque<>();
            while(parent[ind] != ind){
                // stack.push(ind);
                ind = parent[ind];
            }
            //now ind is the index of ultimate father
            //next step is to connect those nodes to father (optimization)
            // while(!stack.isEmpty()){
            //     int child = stack.pop();
            //     parent[child] = ind;
            // }
            return ind;
        }

        public void union(int i1, int j1, int i2, int j2){
            if(i1 < 0 || i1 >= this.row ||
                    j1 < 0 || j1 >= this.col ||
                    i2 <0 || i2 >= this.row ||
                    j2 < 0 || j2 >= this.col ){
                return;
            }

            int ind1 = this.col * i1 + j1;
            int ind2 = this.col * i2 + j2;
            if(visited[ind1] == false || visited[ind2] == false){
                return;
            }

            int parent1 = findFather(ind1);
            int parent2 = findFather(ind2);

            // int size1 = size[parent1];
            // int size2 = size[parent2];
            if(parent1 != parent2){
                if(parent1 > parent2){
                    parent[parent2] = parent1;
                    // size[parent1] += size[parent2];
                }else{
                    parent[parent1] = parent2;
                    // size[parent2] += size[parent1];
                }
                this.setCnt--;
            }
        }

        public int add(int x, int y){
            int ind = this.col * x + y;
            if(visited[ind] == false){
                parent[ind] = ind;
                visited[ind] = true;
                this.setCnt++;
                union(x-1, y, x, y);
                union(x+1, y, x, y);
                union(x, y-1, x, y);
                union(x, y+1, x, y);
            }
            return this.setCnt;
        }
    }
}
