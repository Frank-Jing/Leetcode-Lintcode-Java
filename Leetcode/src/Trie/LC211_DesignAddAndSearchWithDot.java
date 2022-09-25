package Leetcode.src.Trie;

public class LC211_DesignAddAndSearchWithDot {
    class solution{
        /*
    This is based on Trie but the first subNode of TrieNode is set as '.'
    Pro: don't need to do DFS for '.'
    Con: Exceed the Memory Limit when test data is large
     */
        class WordDictionary {
            class TrieNode{
                boolean isWord;
                TrieNode[] next;

                TrieNode(){
                    isWord = false;
                    next = new TrieNode[27];
                }
            }

            private TrieNode root;

            public WordDictionary() {
                root = new TrieNode();
            }

            public void addWord(String word) {
                if(word == null) return;

                helpAdd(word, root);
            }

            private void helpAdd(String word, TrieNode head){
                char c = word.charAt(0);

                if(word.length() == 1){
                    if(head.next[0] == null){
                        head.next[0] = new TrieNode();
                    }
                    head.next[0].isWord = true;

                    if(head.next[c-'a' + 1] == null){
                        head.next[c-'a' + 1] = new TrieNode();
                    }
                    head.next[c-'a' + 1].isWord = true;

                    return;
                }

                TrieNode cur = head;
                TrieNode dotNode = head;

                //handle dot
                if(head.next[0] == null){
                    dotNode.next[0] = new TrieNode();
                }
                dotNode = dotNode.next[0];
                helpAdd(word.substring(1), dotNode);
                //handle real char
                if(cur.next[c-'a' + 1] == null){
                    cur.next[c-'a' + 1] = new TrieNode();
                }
                cur = cur.next[c-'a'+1];
                helpAdd(word.substring(1), cur);

                return;
            }

            public boolean search(String word) {
                if(word == null) return true;

                TrieNode cur = root;
                for(int i=0; i<word.length(); i++){
                    char c = word.charAt(i);
                    if(c == '.'){
                        cur = cur.next[0];
                        if(cur == null){
                            return false;
                        }
                        continue;
                    }
                    if(cur.next[c-'a'+1] != null){
                        cur = cur.next[c-'a' + 1];
                    }else{
                        return false;
                    }
                }
                return cur.isWord;
            }
        }
    }

    class solution2{
        class WordDictionary {
            private WordDictionary[] children;
            boolean isEndOfWord;
            // Initialize your data structure here.
            public WordDictionary() {
                children = new WordDictionary[26];
                isEndOfWord = false;
            }

            // Adds a word into the data structure.
            public void addWord(String word) {
                WordDictionary curr = this;
                for(char c: word.toCharArray()){
                    if(curr.children[c - 'a'] == null)
                        curr.children[c - 'a'] = new WordDictionary();
                    curr = curr.children[c - 'a'];
                }
                curr.isEndOfWord = true;
            }

            // Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
            public boolean search(String word) {
                WordDictionary curr = this;
                for(int i = 0; i < word.length(); ++i){
                    char c = word.charAt(i);
                    if(c == '.'){
                        for(WordDictionary ch: curr.children)
                            if(ch != null && ch.search(word.substring(i+1))) return true;
                        return false;
                    }
                    if(curr.children[c - 'a'] == null) return false;
                    curr = curr.children[c - 'a'];
                }
                return curr != null && curr.isEndOfWord;
            }
        }
    }
}
