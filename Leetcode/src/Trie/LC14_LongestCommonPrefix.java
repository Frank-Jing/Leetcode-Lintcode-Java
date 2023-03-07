package Leetcode.src.Trie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LC14_LongestCommonPrefix {
    class Solution_trie {

        class TrieNode{
            Map<Character, TrieNode> next;
            boolean wordEnd;

            TrieNode(){
                next = new HashMap<>();
            }
        }

        class Trie{
            TrieNode root;
            Trie(){
                root = new TrieNode();
            }

            public void insert(String str){
                TrieNode node = root;
                for(char c : str.toCharArray()){
                    if(!node.next.containsKey(c)){
                        node.next.put(c, new TrieNode());
                    }
                    node = node.next.get(c);
                }
                node.wordEnd = true;
            }

            public String lcp(){
                TrieNode node = root;
                StringBuilder ans = new StringBuilder();
                while(node.next != null && node.next.size() == 1 && !node.wordEnd){
                    Iterator<Map.Entry<Character, TrieNode>> iter = node.next.entrySet().iterator();

                    while(iter.hasNext()){
                        Map.Entry<Character, TrieNode> entry = iter.next();
                        ans.append(entry.getKey());
                        node = entry.getValue();
                    }
                }

                return ans.toString();
            }
        }

        public String longestCommonPrefix(String[] strs) {
            if(strs.length == 1) return strs[0];

            Trie trie = new Trie();

            for(String s : strs){
                trie.insert(s);
            }

            return trie.lcp();
        }
    }

    class Solution_compareEach{
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) return "";
            String prefix = strs[0];
            for (int i = 1; i < strs.length; i++)
                while (strs[i].indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) return "";
                }
            return prefix;
        }
    }

    class Solution_divdeCon{
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            return longestCommonPrefix(strs, 0 , strs.length - 1);
        }

        private String longestCommonPrefix(String[] strs, int l, int r) {
            if (l == r) {
                return strs[l];
            }
            else {
                int mid = (l + r)/2;
                String lcpLeft =   longestCommonPrefix(strs, l , mid);
                String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
                return commonPrefix(lcpLeft, lcpRight);
            }
        }

        String commonPrefix(String left,String right) {
            int min = Math.min(left.length(), right.length());
            for (int i = 0; i < min; i++) {
                if ( left.charAt(i) != right.charAt(i) )
                    return left.substring(0, i);
            }
            return left.substring(0, min);
        }
    }
}
