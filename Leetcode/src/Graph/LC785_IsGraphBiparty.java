package Leetcode.src.Graph;

public class LC785_IsGraphBiparty {
    class Solution {
        public boolean isBipartite(int[][] graph) {
            int[] colors = new int[graph.length];
            for(int i = 0; i< graph.length; i++){
                colors[i] = -1;
            }

            boolean ans = true;
            for(int i = 0; i<graph.length; i++){
                if(colors[i] == -1){
                    ans &= dfs(graph, colors, i, 0);
                }
                if(!ans) break;
            }

            return ans;
        }

        public boolean dfs(int[][] graph, int[] colors, int curNode, int paint){
            if(colors[curNode] != -1) return colors[curNode] == paint;

            colors[curNode] = paint;
            boolean ans = true;
            for(int next : graph[curNode]){
                ans &= dfs(graph, colors, next, paint^1);
            }

            return ans;
        }
    }
}
