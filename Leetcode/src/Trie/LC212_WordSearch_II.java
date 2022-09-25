package Leetcode.src.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC212_WordSearch_II {
    class TrieNode{
        public Map<Character, TrieNode> next;
        public String word;

        TrieNode(){
            next = new HashMap<>();
            word = null;
        }
    }

    class Solution {

        char[][] boardCopy;
        List<String> results = new ArrayList<>();

        public List<String> findWords(char[][] board, String[] words) {
            //add word into Trie
            TrieNode root = new TrieNode();
            for(String word : words){
                TrieNode cur = root;
                for(char c: word.toCharArray()){
                    if(!cur.next.containsKey(c)){
                        cur.next.put(c, new TrieNode());
                    }
                    cur = cur.next.get(c);
                }
                cur.word = word;
            }

            //search
            this.boardCopy = board;
            int rowCnt = this.boardCopy.length;
            int colCnt = this.boardCopy[0].length;

            for(int i = 0; i< rowCnt; i++){
                for(int j=0; j<colCnt; j++){
                    Character start = board[i][j];
                    if(root.next.containsKey(start)){
                        // root = root.next.get(start);
                        BFSCheck(i,j,root);
                    }
                }
            }

            return this.results;
        }

        public void BFSCheck(int row, int col, TrieNode root){
            Character letter = this.boardCopy[row][col];
            TrieNode cur = root.next.get(letter);

            //corner case
            if(cur.word != null){
                this.results.add(cur.word);
                cur.word = null;
            }

            int[][] actions = {{-1,0}, {1,0}, {0,1}, {0,-1}};
            this.boardCopy[row][col] = '@';
            for(int[] action : actions){
                int curRow = row + action[1];
                int curCol = col + action[0];
                if(curRow < 0 || curRow >= this.boardCopy.length ||
                        curCol < 0 || curCol >= this.boardCopy[row].length){
                    continue;
                }
                Character toVisit = this.boardCopy[curRow][curCol];
                if(cur.next.containsKey(toVisit)){
                    BFSCheck(curRow, curCol, cur);
                }


            }
            this.boardCopy[row][col] = letter;

            //this leaf is empty. This would significantly reduce time
            if(cur.next.isEmpty()){
                root.next.remove(letter);
            }
        }
    }
}
