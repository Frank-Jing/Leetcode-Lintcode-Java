package Leetcode.src.Trie;

import java.util.*;

public class LC139_WordBreak {
    class Solution {
        class Trie{
            public Map<Character, Trie> next;
            public boolean isWord;
            Trie(){
                next = new HashMap<>();
                isWord = false;
            }
        }

        public boolean wordBreak(String s, List<String> wordDict) {
            Trie root = new Trie();
            //add word
            for(String word: wordDict){
                Trie cur = root;
                for(char c: word.toCharArray()){
                    if(!cur.next.containsKey(c)){
                        cur.next.put(c, new Trie());
                    }
                    cur = cur.next.get(c);
                }
                cur.isWord = true;
            }

            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            for(int i = 1; i<= s.length(); i++){
                for(int j = 0; j<i; j++){
                    String piece = s.substring(j, i); //[j, i+1)
                    if(dp[j] && search(piece, root)){
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }

        public boolean search(String word, Trie root){
            if(word == null) return true;

            Trie cur = root;
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                if(!cur.next.containsKey(c)){
                    return false;
                }
                cur = cur.next.get(c);
            }
            return cur.isWord;
        }
    }

    class Solution2 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length()+1];
            dp[0] = true; //{} is always true

            for(int i = 1; i<= s.length(); i++){
                for(int j = 0; j<i; j++){
                    if(dp[j] && dict.contains(s.substring(j,i))){
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }
    }

    // https://www.youtube.com/watch?v=2NaaM_z_Jig
// https://www.geeksforgeeks.org/word-break-problem-dp-32/

    class Solution3 {
        // Trie Tree
        class TrieNode {
            TrieNode[] next;
            boolean isWord;

            TrieNode() {
                next = new TrieNode[26];
                isWord = false;
            }
        }

        class Trie {
            TrieNode root;

            Trie() {
                root = new TrieNode();
            }

            public void put(String word) {
                int len = word.length();
                TrieNode node = root;
                for (int i = 0; i < len; i ++) {
                    int c = word.charAt(i) - 'a';
                    if(node.next[c] == null) {
                        node.next[c] = new TrieNode();
                    }
                    node = node.next[c];
                }
                node.isWord = true;
            }
        }

        public boolean wordBreak(String s, List<String> wordDict) {
            Trie trie = new Trie();
            for (String word: wordDict) {
                trie.put(word);
            }

            int len = s.length();
            // if ith char to end could be splited into word in dict
            // dp[len] means empty
            boolean[] dp = new boolean[len + 1];
            dp[len] = true;

            for (int i = len - 1; i >= 0; i --) {
                TrieNode node = trie.root;
                for (int j = i; j < len; j ++) {
                    int c = s.charAt(j) - 'a';
                    if (node.next[c] == null) break;
                    if (node.next[c].isWord) {
                        dp[i] = dp[j + 1];
                        if(dp[i]) break;
                    }
                    node = node.next[c];
                }
            }
            return dp[0];
        }


    }

}
