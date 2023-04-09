package Leetcode.src.UnionFind;

import java.util.*;

public class LC399_EvaluateDivision {
    class Solution_UnionFind {
        class UnionFind {
            Map<String, String> parent;
            Map<String, Map<String, Double>> division;
            Set<String> seen;
            // String[] help;

            public UnionFind (List<List<String>> equations){
                parent = new HashMap<>();
                seen = new HashSet<String>();
                division = new HashMap<>();

                for(int i = 0; i < equations.size(); i++){
                    List<String> pair = equations.get(i);
                    String v1 = pair.get(0);
                    String v2 = pair.get(1);

                    if(!seen.contains(v1)){
                        seen.add(v1);
                        parent.put(v1, v1);
                        Map<String, Double> divid1 = new HashMap<>();
                        divid1.put(v1, (double)1);
                        division.put(v1, divid1);
                    }
                    if(!seen.contains(v2)){
                        seen.add(v2);
                        parent.put(v2, v2);
                        Map<String, Double> divid2 = new HashMap<>();
                        divid2.put(v2, (double)1);
                        division.put(v2, divid2);
                    }
                }
            }

            public String findFather(String var){
                // int hi = 0;
                while(parent.get(var) != var){
                    // help[hi++] = var;
                    var = parent.get(var);
                }

                // while(hi >= 0){
                //     hi--;
                //     parent.put(help[hi], var);
                // }

                return var;
            }
            public void union(String v1, String v2, double val){
                String fa1 = findFather(v1);
                String fa2 = findFather(v2);

                Map<String, Double> divList1 = division.get(fa1);
                Map<String, Double> divList2 = division.get(fa2);

                double fa1v1 = divList1.get(v1);
                double fa2v2 = divList2.get(v2);

                int size1 = divList1.size();
                int size2 = divList2.size();

                if(size1 >= size2){
                    parent.put(fa2, fa1);
                    divList1.put(fa2, fa1v1/fa2v2 * val);
                    divList1.put(v2, fa1v1 * val);
                    for(String other : divList2.keySet()){
                        double fa1fa2 = divList1.get(fa2);
                        divList1.put(other, fa1fa2*divList2.get(other));
                    }
                    // division.remove(fa2);

                }else{
                    parent.put(fa1, fa2);
                    divList2.put(fa1, fa2v2/fa1v1/val);
                    divList2.put(v1, fa2v2/val);
                    for(String other : divList1.keySet()){
                        double fa2fa1 = divList2.get(fa1);
                        divList2.put(other, fa2fa1/divList1.get(other));
                    }
                    // division.remove(fa2);
                }
            }

        }
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            UnionFind uf = new UnionFind(equations);
            for(int i = 0; i<equations.size(); i++){
                List<String> pair = equations.get(i);
                uf.union(pair.get(0), pair.get(1), values[i]);
            }

            double[] ans = new double[queries.size()];
            for(int j=0; j < queries.size(); j++){
                List<String> query = queries.get(j);
                String var1 = query.get(0);
                String var2 = query.get(1);
                if(!uf.seen.contains(var1) || !uf.seen.contains(var2)){
                    ans[j] = (double)-1;
                }else{
                    String fa1 = uf.findFather(var1);
                    String fa2 = uf.findFather(var2);
                    if(fa1.equals(fa2)){
                        double favar1 = uf.division.get(fa1).get(var1);
                        double favar2 = uf.division.get(fa1).get(var2);
                        ans[j] = favar2/favar1;
                    }else{
                        ans[j] = (double)-1;
                    }
                }
            }

            return ans;
        }
    }

    class Solution_DFS_graph {

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, Map<String, Double>> graph = generateGraph(equations, values);

            int n = queries.size();
            double[] ans = new double[n];
            Set<String> set = new HashSet<String>();

            for(int i = 0; i< n; i++){
                String src = queries.get(i).get(0);
                String dst = queries.get(i).get(1);
                set.clear();
                ans[i] = dfs(src, dst, set, 1.0, graph);
            }

            return ans;
        }

        public Map<String, Map<String, Double>> generateGraph(List<List<String>> equations, double[] values){
            Map<String, Map<String, Double>> graph = new HashMap<>();
            for(int i = 0; i<values.length; i++){
                String from = equations.get(i).get(0);
                String to = equations.get(i).get(1);
                double ratio = values[i];
                graph.computeIfAbsent(from, val -> new HashMap<String, Double>()).put(to, ratio);
                graph.computeIfAbsent(to, val -> new HashMap<String, Double>()).put(from, 1/ratio);
            }
            return graph;
        }

        public double dfs(String cur, String dst, Set<String> visit, double ratio, Map<String, Map<String, Double>> graph){
            if(!graph.containsKey(cur)) return -1.0;

            if(cur.equals(dst)){
                return ratio;
            }

            double res = -1.0;
            visit.add(cur);

            for(String next : graph.get(cur).keySet()){
                if(visit.contains(next)) continue;
                res = dfs(next, dst, visit, ratio * graph.get(cur).get(next), graph);
                if(res != -1) break;
            }
            visit.remove(cur);

            return res;
        }
    }


}
