package Leetcode.src.Trie;

public class LC421_MaximumXORofTwoNumbersinanArray {

    class Solution_slow {
        public int findMaximumXOR(int[] nums) {
            if (nums.length == 1) return nums[0];

            int max = 0;
            for(int i = 0; i<nums.length-1; i++){
                for(int j = i+1; j<nums.length; j++){
                    max = Math.max(nums[i]^nums[j], max);
                }
            }

            return max;
        }
    }

    class Solution {

        class TrieNode{
            int val;
            int bit;
            TrieNode[] next;

            TrieNode(int num){
                bit = num;
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
        }


        public int findMaximumXOR(int[] nums) {
            if (nums.length == 1) return 0;
            int max = 0;
            Trie trie = new Trie();
            for(int num : nums){
                trie.put(num);
            }

            for(int num : nums){
                TrieNode node = trie.root;
                for(int i =31; i>=0; i--){
                    int bitOppo = 1 - ((num>>i)&1);
                    if(node.next[bitOppo] == null){
                        node = node.next[1 - bitOppo];
                    }else{
                        node = node.next[bitOppo];
                    }
                }
                max = Math.max(num^node.val, max);
            }

            return max;
        }
    }

}
