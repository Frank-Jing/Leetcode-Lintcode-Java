package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC51_NQueens {
    class Solution {
        List<List<String>> results;
        int size;
        public List<List<String>> solveNQueens(int n) {
            size = n;
            results = new ArrayList<>();
            int[] records = new int[n];
            Arrays.fill(records, -1);

            backtrack(0, records);
            return results;
        }

        public void backtrack(int i, int[] records){
            if(i == size){
                List<String> board = getBoard(records);
                results.add(board);
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

        public List<String> getBoard(int[] records){
            List<String> board = new ArrayList<>();
            for(int i = 0; i < size; i++){
                char[] row = new char[size];
                Arrays.fill(row, '.');
                Integer placeQueenCol = records[i];
                row[placeQueenCol] = 'Q';
                board.add(new String(row));
            }

            return board;
        }

    }
}
