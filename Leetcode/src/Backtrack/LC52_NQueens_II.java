package Leetcode.src.Backtrack;

import java.util.Arrays;

public class LC52_NQueens_II {
    class Solution {
        int size;
        int cnt = 0;
        public int totalNQueens(int n) {
            size = n;
            int[] records = new int[n];
            Arrays.fill(records, -1);

            backtrack(0, records);
            return cnt;
        }

        public void backtrack(int i, int[] records){
            if(i == size){
                cnt++;
                return;
            }

            for(int j = 0; j < size; j++){
                if(valid(i, j, records)){
                    records[i] = j;
                    backtrack(i+1, records);
                    records[i] = -1;
                }
            }

            return;
        }

        public boolean valid(int row, int col, int[] records){
            if(row == 0) return true;

            for(int pre = 0; pre < row; pre++){
                if(records[pre] == col){
                    return false;
                }
                if(Math.abs(row - pre) == Math.abs(col - records[pre])){
                    return false;
                }
            }

            return true;
        }
    }
}
