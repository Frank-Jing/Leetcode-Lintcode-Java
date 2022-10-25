package Lintcode.src.Graph;

import java.util.*;

public class LN127_ToplogicalSort {
    public class Solution {
        /**
         * @param graph: A list of Directed graph node
         * @return: Any topological order for the given graph.
         */
        public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            Map<DirectedGraphNode, Integer> inMap = new HashMap<>();
            for(DirectedGraphNode node : graph){
                inMap.put(node, 0);
            }

            for(DirectedGraphNode node : graph){
                for(DirectedGraphNode next : node.neighbors){
                    inMap.put(next, inMap.get(next) + 1);
                }
            }

            Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
            for(DirectedGraphNode cur : inMap.keySet()){
                if(inMap.get(cur) == 0){
                    zeroQueue.add(cur);
                }
            }

            ArrayList<DirectedGraphNode> ans = new ArrayList<>();
            while(!zeroQueue.isEmpty()){
                DirectedGraphNode cur = zeroQueue.poll();
                ans.add(cur);
                for(DirectedGraphNode next : cur.neighbors){
                    inMap.put(next, inMap.get(next) - 1);
                    if(inMap.get(next) == 0){
                        zeroQueue.add(next);
                    }
                }
            }

            return ans;
        }
    }
}
