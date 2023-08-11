package Leetcode.src.Backtrack;

public class LC79_WordSearch {
    class Solution {
        boolean find = false;
        int m;
        int n;
        public boolean exist(char[][] board, String word) {
            m = board.length;
            n = board[0].length;
            boolean[][] visited = new boolean[m][n];

            char[] chars = word.toCharArray();

            for(int i = 0; i < m; i++){
                for(int j=0; j < n; j++){
                    if(board[i][j] == chars[0]){
                        visited[i][j] = true;
                        backtrack(board, 1, i, j, chars, visited);
                        visited[i][j] = false;
                    }
                }
            }

            return find;
        }

        public void backtrack(char[][] board, int cur, int x, int y, char[] chars, boolean[][] visited){
            if(find) return;
            if(cur == chars.length){
                find = true;
                return;
            }

            int[] direct = new int[]{-1, 1};

            for(int dx : direct){
                if(x + dx < m && x + dx >= 0 && !visited[x+dx][y] && chars[cur] == board[x+dx][y]){
                    visited[x+dx][y] = true;
                    backtrack(board, cur+1, x+dx, y, chars, visited);
                    visited[x+dx][y] = false;
                }
            }

            for(int dy : direct){
                if(y + dy < n && y + dy >= 0 && !visited[x][y+dy] && chars[cur] == board[x][y+dy]){
                    visited[x][y+dy] = true;
                    backtrack(board, cur+1, x, y+dy, chars, visited);
                    visited[x][y+dy] = false;
                }
            }
        }
    }
}
