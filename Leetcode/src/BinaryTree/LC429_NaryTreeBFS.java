package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC429_NaryTreeBFS {
    class Solution_BFS {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> ans = new LinkedList<>();

            if(root == null) return ans;

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while(!queue.isEmpty()){
                int size = queue.size();
                List<Integer> level = new LinkedList<>();
                for(int i = 0; i<size; i++){
                    Node cur = queue.poll();
                    level.add(cur.val);
                    if(cur.children != null){
                        queue.addAll(cur.children);
                    }
                }
                ans.add(level);
            }

            return ans;
        }
    }

    class Solution_recurisive {
        List<List<Integer>> ans = new LinkedList<>();

        public List<List<Integer>> levelOrder(Node root) {
            if(root != null) process(root, 0);
            return ans;
        }

        public void process(Node head, int level){
            if(ans.size() <= level){
                ans.add(new LinkedList<Integer>());
            }
            ans.get(level).add(head.val);
            if(head.children != null){
                for(Node child : head.children){
                    process(child, level + 1);
                }
            }
        }
    }
}
