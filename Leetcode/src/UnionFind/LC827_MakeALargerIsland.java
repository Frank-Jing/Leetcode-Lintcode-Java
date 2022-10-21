package Leetcode.src.UnionFind;

import java.util.HashSet;
import java.util.Set;

public class LC827_MakeALargerIsland {
    class Solution {

        public int largestIsland(int[][] grid) {
            UnionFind uf = new UnionFind(grid);
            int max = 0;

            for(int i = 1; i<grid.length; i++){
                if(grid[i][0] == 1 && grid[i-1][0] == 1){
                    uf.union(i,0, i-1, 0);
                }
            }

            for(int j = 1; j < grid[0].length; j++){
                if(grid[0][j] == 1 && grid[0][j-1] == 1){
                    uf.union(0, j, 0, j-1);
                }
            }

            for(int i = 1; i < grid.length; i++){
                for(int j = 1; j < grid[0].length; j++){
                    if(grid[i][j] == 1){
                        if(grid[i][j-1] == 1){
                            uf.union(i,j, i, j-1);
                        }
                        if(grid[i-1][j] == 1){
                            uf.union(i, j, i-1, j);
                        }
                    }
                }
            }

            max = uf.maxSize;
            if(max != 0){
                for(int i = 0; i< grid.length; i++){
                    for(int j = 0; j < grid[0].length; j++){
                        if(grid[i][j] == 0){
                            int potentSize = addConnection(i, j, uf, grid);
                            max = Math.max(max, potentSize);
                        }
                    }
                }
            }


            return max == 0? 1: max;
        }

        public int addConnection(int i, int j, UnionFind uf, int[][] grid){
            Set<Integer> visistedParent = new HashSet<>();
            int size = 0;
            if(i - 1 >= 0 && grid[i-1][j] == 1){
                int ind = uf.getInd(i-1, j);
                int father = uf.findFather(ind);
                size += uf.size[father];
                visistedParent.add(father);
            }
            if(i+1 < grid.length && grid[i+1][j] == 1){
                int ind = uf.getInd(i+1, j);
                int father = uf.findFather(ind);
                if(!visistedParent.contains(father)){
                    size += uf.size[father];
                    visistedParent.add(father);
                }
            }

            if(j-1 >= 0 && grid[i][j-1] == 1){
                int ind = uf.getInd(i, j-1);
                int father = uf.findFather(ind);
                if(!visistedParent.contains(father)){
                    size += uf.size[father];
                    visistedParent.add(father);
                }
            }

            if(j+1 < grid[0].length && grid[i][j+1] == 1){
                int ind = uf.getInd(i, j+1);
                int father = uf.findFather(ind);
                if(!visistedParent.contains(father)){
                    size += uf.size[father];
                    visistedParent.add(father);
                }
            }

            return size == 0? 0 : size+1; //include the point
        }
    }

    class UnionFind {
        int[] parent;
        int[] size;
        int maxSize;
        int col;

        public UnionFind (int[][] grid){
            this.col = grid[0].length;
            int len = grid.length * this.col;

            parent = new int[len];
            size = new int[len];

            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < this.col; j++){
                    if(grid[i][j] == 1){
                        int ind = getInd(i, j);
                        parent[ind] = ind;
                        size[ind] = 1;
                        maxSize = 1;
                    }
                }
            }
        }

        public int getInd(int i, int j){
            return i*this.col + j;
        }

        public int findFather(int ind){
            while(ind != parent[ind]){
                ind = parent[ind];
            }
            return ind;
        }

        public void union(int i1, int j1, int i2, int j2){
            int ind1 = getInd(i1, j1);
            int ind2 = getInd(i2, j2);

            int fa1 = findFather(ind1);
            int fa2 = findFather(ind2);
            int size1 = size[fa1];
            int size2 = size[fa2];
            if(fa1 != fa2){
                if(size1 >= size2){
                    parent[fa2] = fa1;
                    size[fa1] += size[fa2];
                    maxSize = Math.max(maxSize, size[fa1]);
                }else{
                    parent[fa1] = fa2;
                    size[fa2] += size[fa1];
                    maxSize = Math.max(maxSize, size[fa2]);
                }
            }
        }
    }
}
