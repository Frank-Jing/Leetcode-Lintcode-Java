package Leetcode.src.UnionFind;

public class LC695_MaxAreaOfIsland {
    class Solution_recursive {
        int max = 0;
        int singleSize = 0;

        public int maxAreaOfIsland(int[][] grid) {
            for(int i = 0; i< grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == 1){
                        singleSize = 0;
                        dfs(grid, i, j);
                        max = Math.max(max, singleSize);
                    }
                }
            }
            return max;
        }

        private void dfs(int[][] grid, int rowInd, int colInd){
            if(rowInd < 0 || rowInd >= grid.length || colInd < 0 || colInd >= grid[0].length) return;
            if(grid[rowInd][colInd] == 0) return;

            grid[rowInd][colInd] = 0;
            singleSize++;
            dfs(grid, rowInd+1, colInd);
            dfs(grid, rowInd-1, colInd);
            dfs(grid, rowInd, colInd+1);
            dfs(grid, rowInd, colInd-1);
        }
    }

    class Solution_unionFind {
        public int maxAreaOfIsland(int[][] grid) {
            UnionFind uf = new UnionFind(grid);
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

            return uf.maxSize;
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
                            int ind = i*this.col + j;
                            parent[ind] = ind;
                            size[ind] = 1;
                            maxSize = 1;
                        }
                    }
                }
            }

            public int findFather(int ind){
                while(ind != parent[ind]){
                    ind = parent[ind];
                }
                return ind;
            }

            public void union(int i1, int j1, int i2, int j2){
                int ind1 = this.col*i1 + j1;
                int ind2 = this.col*i2 + j2;

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
}
