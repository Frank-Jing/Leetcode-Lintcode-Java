package Leetcode.src.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class LC990_SatisfiabilityOfEquations {
    class Solution_unionFind_list {
        public boolean equationsPossible(String[] equations) {
            int[] parent = new int[26];
            for(int i = 0; i< parent.length; i++){
                parent[i] = i;
            }

            for(String eq : equations){
                if(eq.charAt(1) == '='){
                    union(parent, eq.charAt(0) - 'a', eq.charAt(3) - 'a');
                }
            }

            for(String eq:equations){
                if(eq.charAt(1) == '!'){
                    if(find(parent, eq.charAt(0) - 'a') == find(parent, eq.charAt(3) - 'a')){
                        return false;
                    }
                }
            }
            return true;
        }

        public int find(int[] parent, int ind){
            while(parent[ind] != ind){
                ind = parent[ind];
            }
            return ind;
        }

        public void union(int[] parent, int i, int j){
            int f1 = find(parent, i);
            int f2 = find(parent, j);
            parent[f2] = f1;
        }
    }


    class Solution_unionFind_HashTable {
        public boolean equationsPossible(String[] equations) {
            UnionFind uf = new UnionFind(equations);
            boolean valid = true;
            for(String eq : equations){
                String relation = eq.substring(1,3);
                if(relation.equals("==")){
                    char a = eq.charAt(0);
                    char b = eq.charAt(3);
                    uf.union(a, b);
                }
            }

            int ind = 0;
            while(valid == true && ind < equations.length){
                String eq = equations[ind++];
                String relation = eq.substring(1,3);
                if(relation.equals("!=")){
                    char a = eq.charAt(0);
                    char b = eq.charAt(3);
                    if(uf.findFather(a) == uf.findFather(b)){
                        valid = false;
                    }
                }
            }

            return valid;
        }
    }

    class UnionFind {
        Map<Character, Character> parent = new HashMap<>();
        Map<Character, Integer> size = new HashMap<>();

        public UnionFind (String[] eqs){
            for(String eq : eqs){
                char a = eq.charAt(0);
                char b = eq.charAt(3);
                if(!parent.containsKey(a)){
                    parent.put(a, a);
                    size.put(a, 1);
                }
                if(!parent.containsKey(b)){
                    parent.put(b, b);
                    size.put(b, 1);
                }
            }
        }

        public char findFather(char c){
            while(parent.get(c) != c){
                c = parent.get(c);
            }
            return c;
        }

        public void union(char a, char b){
            char fa = findFather(a);
            char fb = findFather(b);

            if(fa != fb){
                int size1 = size.get(fa);
                int size2 = size.get(fb);
                char newFather = size1 >= size2? fa : fb;
                char newChild = newFather == fa? fb : fa;
                parent.put(newChild, newFather);
                size.put(newFather, size1 + size2);
                size.remove(newChild);
            }

        }
    }

}
