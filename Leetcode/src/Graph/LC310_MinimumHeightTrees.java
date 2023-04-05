package Leetcode.src.Graph;

import java.util.*;

public class LC310_MinimumHeightTrees {
    class Solution_Topology_TLE {

        public class Node{
            public int val;
            public List<Node> neighbors;
            public int deep;
            public int treeHeight;
            public Node(int val, List<Node> neighbors){
                this.val = val;
                this.neighbors = neighbors;
                this.deep = 0;
            }
        }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> ans = new ArrayList<>();
            if(n == 1){
                ans.add(0);
                return ans;
            }
            Node[] graph = generateGraph(n, edges);
            Queue<Node> heap = new PriorityQueue<>((a, b) -> a.treeHeight - b.treeHeight);
            for(int i = 0; i<n; i++){
                Node cur = graph[i];
                cur.treeHeight = f(cur, cur);
                heap.offer(cur);
            }
            int min = heap.peek().treeHeight;
            while(heap.size() > 0){
                Node cur = heap.poll();
                if(cur.treeHeight == min){
                    ans.add(cur.val);
                }else{
                    break;
                }
            }
            return ans;
        }

        // Generate graph based on edge info
        // Time Complexity O(N)
        public Node[] generateGraph(int n , int[][] edges){
            Node[] graph = new Node[n];

            Set<Integer> visited = new HashSet<>();

            for(int i=0; i < edges.length; i++){
                int from = edges[i][0];
                int to = edges[i][1];

                if(!visited.contains(from)){
                    Node nodeFrom = new Node(from, new ArrayList<Node>());
                    graph[from] = nodeFrom;
                    visited.add(from);
                }
                if(!visited.contains(to)){
                    Node nodeTo = new Node(to, new ArrayList<Node>());
                    graph[to] = nodeTo;
                    visited.add(to);
                }

                graph[from].neighbors.add(graph[to]);
                graph[to].neighbors.add(graph[from]);
            }

            return graph;
        }

        // For each node, start as root, get the max depth
        public int f(Node cur, Node fatherNode) {
            int follow = 0;
            for (Node next : cur.neighbors) {
                if(next.val != fatherNode.val && next.val != cur.val){
                    follow = Math.max(follow, f(next, cur));
                }
            }
            int ans = follow + 1;
            cur.deep = ans;
            return ans;
        }
    }


    class Solution_Topology_minStack_TLE {

        public class Node{
            public int val;
            public List<Node> neighbors;
            public int treeHeight;
            public Node(int val, List<Node> neighbors){
                this.val = val;
                this.neighbors = neighbors;
            }
        }

        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            List<Integer> ans = new ArrayList<>();
            if(n == 1){
                ans.add(0);
                return ans;
            }
            Node[] graph = generateGraph(n, edges);
            Deque<Node> minStack = new ArrayDeque<>();
            HashMap<Integer, Integer> record = new HashMap<>();
            // for each node, start as root and get max Depth
            // total time complexity O(N^2)
            for(int i = 0; i<n; i++){
                Node cur = graph[i];
                cur.treeHeight = f(cur, cur, record);
                record.clear();

                updateMinStack(minStack, cur);
            }

            while(!minStack.isEmpty()){
                Node cur = minStack.poll();
                ans.add(cur.val);
            }
            return ans;
        }

        public void updateMinStack(Deque<Node> stack, Node node){
            while(!stack.isEmpty() && stack.peek().treeHeight > node.treeHeight){
                stack.poll();
            }
            if(stack.isEmpty() || stack.peek().treeHeight == node.treeHeight){
                stack.push(node);
            }
        }

        // Time complexity O(N)
        public Node[] generateGraph(int n , int[][] edges){
            Node[] graph = new Node[n];

            for(int i=0; i < edges.length; i++){
                int from = edges[i][0];
                int to = edges[i][1];

                if(graph[from] == null){
                    Node nodeFrom = new Node(from, new ArrayList<Node>());
                    graph[from] = nodeFrom;
                }
                if(graph[to] == null){
                    Node nodeTo = new Node(to, new ArrayList<Node>());
                    graph[to] = nodeTo;
                }
                graph[from].neighbors.add(graph[to]);
                graph[to].neighbors.add(graph[from]);
            }

            return graph;
        }

        // For each node, start as root, get the max depth
        // using the memory table to store, time complexity O(N)
        public int f(Node cur, Node fatherNode, HashMap<Integer, Integer> record) {
            if(record.containsKey(cur.val)){
                return record.get(cur.val);
            }
            int follow = 0;
            for (Node next : cur.neighbors) {
                if(next.val != fatherNode.val && next.val != cur.val){
                    follow = Math.max(follow, f(next, cur, record));
                }
            }
            int ans = follow + 1;
            record.put(cur.val, ans);
            return ans;
        }
    }

    class Solution_Topology {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {

            // edge cases
            if (n < 2) {
                ArrayList<Integer> centroids = new ArrayList<>();
                for (int i = 0; i < n; i++)
                    centroids.add(i);
                return centroids;
            }

            // Build the graph with the adjacency list
            ArrayList<Set<Integer>> neighbors = new ArrayList<>();
            for (int i = 0; i < n; i++)
                neighbors.add(new HashSet<Integer>());

            for (int[] edge : edges) {
                Integer start = edge[0], end = edge[1];
                neighbors.get(start).add(end);
                neighbors.get(end).add(start);
            }

            // Initialize the first layer of leaves
            ArrayList<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (neighbors.get(i).size() == 1)
                    leaves.add(i);

            // Trim the leaves until reaching the centroids
            int remainingNodes = n;
            while (remainingNodes > 2) {
                remainingNodes -= leaves.size();
                ArrayList<Integer> newLeaves = new ArrayList<>();

                // remove the current leaves along with the edges
                for (Integer leaf : leaves) {
                    // the only neighbor left for the leaf node
                    Integer neighbor = neighbors.get(leaf).iterator().next();
                    // remove the edge along with the leaf node
                    neighbors.get(neighbor).remove(leaf);
                    if (neighbors.get(neighbor).size() == 1)
                        newLeaves.add(neighbor);
                }

                // prepare for the next round
                leaves = newLeaves;
            }

            // The remaining nodes are the centroids of the graph
            return leaves;
        }
    }
}
