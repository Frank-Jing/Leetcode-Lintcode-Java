package Leetcode.src.Graph;

import java.util.*;

public class LC797_AllPathsFromSourcetoTarget {
    class Solution {
        int detination;
        List<List<Integer>> allPaths;

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            detination = graph.length - 1;
            allPaths = new ArrayList<>();

            LinkedList<Integer> path = new LinkedList<>();
            dfs(0, graph, path);

            return allPaths;
        }

        public void dfs(int cur, int[][] graph, LinkedList<Integer> path){
            path.add(cur);
            if(cur == detination){
                allPaths.add(new ArrayList<Integer>(path) ); // avoid memory value change
            }else{
                for(int next : graph[cur]){
                    dfs(next, graph, path);
                }
            }
            path.removeLast();

            return;
        }
    }
}
