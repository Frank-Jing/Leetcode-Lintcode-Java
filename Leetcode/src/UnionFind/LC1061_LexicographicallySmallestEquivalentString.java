package Leetcode.src.UnionFind;

public class LC1061_LexicographicallySmallestEquivalentString {
    class Solution_unionFind {
        class UnionFind{
            int[] parent;

            UnionFind(){
                parent = new int[26];
                for(int i = 0; i< 26; i++){
                    parent[i] = i;
                }
            }

            public int findParent(char a){
                int ind = a - 'a';
                while(parent[ind] != ind){
                    ind = parent[ind];
                }
                return ind;
            }

            public void union(char x, char y){
                int a = findParent(x);
                int b = findParent(y);
                if(a == b){
                    return;
                }
                if(a < b){
                    parent[b] = a;
                }else{
                    parent[a] = b;
                }
            }
        }
        public String smallestEquivalentString(String s1, String s2, String baseStr) {
            int n = s1.length();
            UnionFind uf = new UnionFind();
            for(int i = 0; i< n; i++){
                uf.union(s1.charAt(i), s2.charAt(i));
            }

            StringBuilder ans = new StringBuilder();
            for(char c : baseStr.toCharArray()){
                int ind = uf.findParent(c);
                char pc = (char)('a' + ind);
                ans.append(pc);
            }

            return ans.toString();

        }
    }
}
