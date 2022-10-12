package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102_BinaryTreeLevelOrderTraverse {
    class Solution_recursve {
        List<List<Integer>> ans = new LinkedList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if(root != null) process(root, 0);
            return ans;
        }

        public void process(TreeNode head, int level){
            if(ans.size() <= level){
                ans.add(new LinkedList<Integer>());
            }
            if(head != null){
                ans.get(level).add(head.val);
                if(head.left != null || head.right != null){
                    process(head.left, level + 1);
                    process(head.right, level + 1);
                }
            }
        }
    }

    class Solution_BFS {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ans = new LinkedList<>();
            if(root == null) return ans;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()){
                List<Integer> level = new LinkedList<>();
                int size = queue.size();
                for(int i=0; i< size; i++){
                    TreeNode cur = queue.poll();
                    level.add(cur.val);
                    if(cur.left != null) queue.offer(cur.left);
                    if(cur.right != null) queue.offer(cur.right);
                }
                ans.add(level);
            }

            return ans;
        }
    }

}
