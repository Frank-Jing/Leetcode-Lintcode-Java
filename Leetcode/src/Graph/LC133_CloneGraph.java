package Leetcode.src.Graph;

import java.util.*;

public class LC133_CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class Solution_DFS {
        Map<Integer, Node> visited = new HashMap<>();
        public Node cloneGraph(Node node) {
            if(node == null) return null;
            if(visited.containsKey(node.val)){
                return visited.get(node.val);
            }

            Node copyNode = new Node(node.val);
            visited.put(node.val, copyNode);

            List<Node> neighbors = new ArrayList<>();
            for(Node next : node.neighbors){
                neighbors.add(cloneGraph(next));
            }
            copyNode.neighbors = neighbors;

            return copyNode;
        }
    }

    class Solution_DFS_II {
        public Node cloneGraph(Node node) {
            if(node == null){
                return null;

            }
            HashMap<Integer,Node> visited = new HashMap<>();
            return helper(node,visited);

        }
        public Node helper(Node node,HashMap<Integer,Node> visited){
            Node nodeClone = new Node(node.val);
            visited.put(node.val,nodeClone);

            for(Node nbr:node.neighbors){
                if(visited.containsKey(nbr.val) == false){
                    Node nbrClone = helper(nbr,visited);
                    nodeClone.neighbors.add(nbrClone);
                }else{
                    Node nbrClone = visited.get(nbr.val);
                    nodeClone.neighbors.add(nbrClone);
                }
            }
            return nodeClone;
        }
    }

    class Solution_BFS {
        public Node cloneGraph(Node node) {
            if(node == null) return null;

            HashMap<Node, Node> visited = new HashMap<>();
            visited.put(node, new Node(node.val, new ArrayList<Node>()) );
            Queue<Node> queue = new LinkedList<>();
            queue.offer(node);

            while(!queue.isEmpty()){
                Node cur = queue.poll();
                for(Node next : cur.neighbors){
                    if(!visited.containsKey(next)){
                        visited.put(next, new Node(next.val, new ArrayList<Node>()));
                        queue.offer(next);
                    }
                    visited.get(cur).neighbors.add(visited.get(next));
                }
            }

            return visited.get(node);
        }
    }
}

