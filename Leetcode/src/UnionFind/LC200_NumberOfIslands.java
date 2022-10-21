package Leetcode.src.UnionFind;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC200_NumberOfIslands {
    class Solution_recursive {
        public int numIslands(char[][] grid) {

            int cnt = 0;
            for(int i = 0; i< grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == '1'){
                        cnt++;
                        dfs(grid, i, j);
                    }
                }
            }
            return cnt;
        }

        private void dfs(char[][] grid, int rowInd, int colInd){
            if(rowInd < 0 || rowInd >= grid.length || colInd < 0 || colInd >= grid[0].length) return;
            if(grid[rowInd][colInd] == '0') return;

            grid[rowInd][colInd] = '0';
            dfs(grid, rowInd+1, colInd);
            dfs(grid, rowInd-1, colInd);
            dfs(grid, rowInd, colInd+1);
            dfs(grid, rowInd, colInd-1);
        }
    }

    class Solution_UnionFind {
        public int numIslands(char[][] grid) {
            UnionFind uf = new UnionFind(grid);
            for(int i = 1; i<grid.length; i++){
                if(grid[i][0] == '1' && grid[i-1][0] == '1'){
                    uf.union(i,0, i-1, 0);
                }
            }

            for(int j = 1; j < grid[0].length; j++){
                if(grid[0][j] == '1' && grid[0][j-1] == '1'){
                    uf.union(0, j, 0, j-1);
                }
            }

            for(int i = 1; i < grid.length; i++){
                for(int j = 1; j < grid[0].length; j++){
                    if(grid[i][j] == '1'){
                        if(grid[i][j-1] == '1'){
                            uf.union(i,j, i, j-1);
                        }
                        if(grid[i-1][j] == '1'){
                            uf.union(i, j, i-1, j);
                        }
                    }
                }
            }

            return uf.setCnt;
        }


    }

    class UnionFind {
        int[] parent;
        int[] size;
        int col;
        int setCnt;

        public UnionFind (char[][] grid){
            this.col = grid[0].length;
            int rowNum = grid.length;
            int len = rowNum * this.col;

            parent = new int[len];
            size = new int[len];
            for(int i = 0; i < rowNum; i++){
                for(int j = 0; j < this.col; j++){
                    if(grid[i][j] == '1'){
                        int ind = i*this.col + j;
                        parent[ind] = ind;
                        size[ind] = 1;
                        this.setCnt++;
                    }

                }
            }
        }

        public int findFather(int ind){
            Deque<Integer> stack = new ArrayDeque<>();
            while(parent[ind] != ind){
                stack.push(ind);
                ind = parent[ind];
            }
            //now ind is the index of ultimate father
            //next step is to connect those nodes to father (optimization)
            while(!stack.isEmpty()){
                int child = stack.pop();
                parent[child] = ind;
            }

            return ind;
        }

        public void union(int i1, int j1, int i2, int j2){
            int ind1 = this.col * i1 + j1;
            int ind2 = this.col * i2 + j2;
            int parent1 = findFather(ind1);
            int parent2 = findFather(ind2);

            int size1 = size[parent1];
            int size2 = size[parent2];
            if(parent1 != parent2){
                if(size1 >= size2){
                    parent[parent2] = parent1;
                    size[parent1] += size[parent2];
                }else{
                    parent[parent1] = parent2;
                    size[parent2] += size[parent1];
                }
                this.setCnt--;
            }

        }
    }
}
