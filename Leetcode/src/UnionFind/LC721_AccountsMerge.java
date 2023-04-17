package Leetcode.src.UnionFind;

import java.util.*;

public class LC721_AccountsMerge {
    class Solution_TLE {
        List<List<String>> ans;
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();
            UnionFind uf = new UnionFind(n, accounts);
            for(int i = 0; i<n; i++){
                String acctHolder = accounts.get(i).get(0);

                for(int j = i+1; j<n; j++){
                    if(!accounts.get(j).get(0).equals(acctHolder)) continue;
                    uf.union(accounts, i, j);
                }
            }

            ans = new LinkedList<>();
            for(int i : uf.parentList.keySet()){
                LinkedList<String> res = new LinkedList<>();
                for(String email : uf.emailList.get(i)){
                    res.add(email);
                }
                Collections.sort(res);
                res.addFirst(accounts.get(i).get(0));

                ans.add(res);
            }

            return ans;
        }

        class UnionFind{
            List<List<String>> accounts;
            int[] parent;
            Map<Integer, Set<Integer>> parentList;
            Map<Integer, Set<String>> emailList;

            public UnionFind(int n, List<List<String>> accounts){
                parent = new int[n];
                parentList = new HashMap<>();
                emailList = new HashMap<>();
                accounts = accounts;

                for(int i=0; i < n; i++){
                    parent[i] = i;
                    parentList.put(i, new HashSet<>());
                    parentList.get(i).add(i);
                    emailList.put(i, new HashSet<>());
                    for(int j = 1; j < accounts.get(i).size(); j++){
                        emailList.get(i).add(accounts.get(i).get(j));
                    }
                }
            }

            public int find(int x){
                Stack<Integer> help = new Stack<>();
                while(x != parent[x]){
                    help.push(x);
                    x = parent[x];
                }
                while(!help.isEmpty()){
                    int compressedChild = help.pop();
                    parent[compressedChild] = x;
                    merge(x, compressedChild);
                }
                return x;
            }

            public void union(List<List<String>> accounts, int x, int y){
                int a = find(x);
                int b = find(y);

                if(a  == b) return;
                if( !findCommon(a, b)) return;

                int newFather = a;
                int newChild = b;
                if(a < b){
                    parent[b] = a;
                }else if(b < a){
                    parent[a] = b;
                    newFather = b;
                    newChild = a;
                }
                merge(newFather, newChild);

            }

            private void merge(int father, int child){
                for(String s : emailList.get(child)){
                    emailList.get(father).add(s);
                }
                parentList.get(father).add(child);
                parentList.remove(child);
            }

            private boolean findCommon(int a, int b){
                if(parentList.get(a).contains(b) || parentList.get(b).contains(a)) return false;
                Set<String> list1 = emailList.get(a);
                Set<String> list2 = emailList.get(b);
                boolean ans = false;
                for(String email : list2){
                    if(list1.contains(email)){
                        ans = true;
                        break;
                    }
                }
                return ans;
            }
        }
    }

    class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();
            UnionFind uf = new UnionFind(n);
            Map<String, Integer> emailInd = new HashMap<>();
            for(int i =0; i< n; i++){
                for(int j = 1; j<accounts.get(i).size(); j++){
                    String email = accounts.get(i).get(j);
                    if(!emailInd.containsKey(email)){
                        emailInd.put(email, i);
                    }else{
                        uf.union(emailInd.get(email), i);
                    }
                }
            }

            Map<Integer, LinkedList<String>> group = new HashMap<>();
            for(String email : emailInd.keySet()){
                int i = emailInd.get(email);
                int parent = uf.find(i);
                if(!group.containsKey(parent)){
                    group.put(parent, new LinkedList<>());
                }
                group.get(parent).add(email);
            }

            List<List<String>> ans = new ArrayList<>();
            for(int i : group.keySet()){
                LinkedList<String> people = group.get(i);
                Collections.sort(people);
                people.addFirst(accounts.get(i).get(0));
                ans.add(people);
            }

            return ans;

        }

        class UnionFind{
            int[] parent;
            int[] help;

            UnionFind(int n){
                parent = new int[n];
                help = new int[n];
                for(int i = 0; i < n; i++){
                    parent[i] = i;
                }
            }

            public int find(int x){
                int ind = 0;
                while(x != parent[x]){
                    help[ind++] = x;
                    x = parent[x];
                }

                while(--ind >=0){
                    parent[help[ind]] = x;
                }
                return x;
            }

            public void union(int x, int y){
                int a = find(x);
                int b = find(y);
                if(a == b) return;
                if(a < b){
                    parent[b] = a;
                }else{
                    parent[a] = b;
                }
            }


        }
    }
}
