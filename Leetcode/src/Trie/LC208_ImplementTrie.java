package Leetcode.src.Trie;

public class LC208_ImplementTrie {
    class Trie {
        class Node{
            public int pass;
            public int end;
            public Node[] nexts;

            public Node(){
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if(word == null) return;

            char[] chs = word.toCharArray();
            Node cur = root;
            cur.pass++;
            for(int i = 0; i < chs.length; i++){
                int ind = chs[i] - 'a';
                if(cur.nexts[ind] == null){
                    cur.nexts[ind] = new Node();
                }
                cur = cur.nexts[ind];
                cur.pass++;
            }
            cur.end++;
        }

        public boolean search(String word) {
            if(word == null) return false;

            Node cur = root;
            char[] chs = word.toCharArray();
            for(int i=0; i<chs.length; i++){
                int ind = chs[i] - 'a';
                if(cur.nexts[ind] == null){
                    return false;
                }
                cur = cur.nexts[ind];
            }

            return cur.end > 0;
        }

        public boolean startsWith(String prefix) {
            if(prefix == null) return true;

            Node cur = root;
            char[] chs = prefix.toCharArray();
            for(int i=0; i<chs.length; i++){
                int ind = chs[i] - 'a';
                if(cur.nexts[ind] == null){
                    return false;
                }
                cur = cur.nexts[ind];
            }

            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
