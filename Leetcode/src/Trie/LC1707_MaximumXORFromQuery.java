package Leetcode.src.Trie;

import java.util.Arrays;

public class LC1707_MaximumXORFromQuery {
    class Solution {

        public int[] maximizeXor(int[] nums, int[][] queries) {
            Trie trie = new Trie();
            Arrays.sort(nums);
            int[][] queryNew = new int[queries.length][3];
            for(int i = 0; i<queries.length; i++){
                queryNew[i][0] = queries[i][0];
                queryNew[i][1] = queries[i][1];
                queryNew[i][2] = i;
            }
            Arrays.sort(queryNew, (a, b) -> (a[1] - b[1]));

            int[] ans = new int[queryNew.length];
            int ind = 0;
            for(int[] q : queryNew){
                while(ind < nums.length && nums[ind] <= q[1]){
                    trie.put(nums[ind++]);
                }
                if(ind == 0){
                    ans[q[2]] = -1;
                    continue;
                }
                ans[q[2]] = trie.findMaxXor(q[0], q[1]);
            }
            return ans;
        }

        class TrieNode{
            int val; //final value;
            int bit;
            TrieNode[] next;

            TrieNode(int i){
                this.bit = i;
                next = new TrieNode[2];
            }
            TrieNode(){
                next = new TrieNode[2];
            }
        }

        class Trie{
            TrieNode root;
            Trie(){root = new TrieNode();}

            public void put(int num){
                TrieNode node = root;
                for(int i = 31; i>= 0; i--){
                    int bit = (num >> i)&1;
                    if(node.next[bit] == null){
                        node.next[bit] = new TrieNode(bit);
                    }
                    node = node.next[bit];
                }
                node.val = num;
            }

            public int findMaxXor(int target, int limit){
                TrieNode node = root;
                int ret = 0;
                for(int i =31; i>=0; i--){
                    int bitOppo = 1 - ((target>>i)&1);
                    if(node.next[bitOppo] != null){
                        node = node.next[bitOppo];
                        ret = ret*2+1;
                    }else{
                        node = node.next[1-bitOppo];
                        ret = ret*2;
                    }
                }
                return ret;
            }
        }
    }
}
