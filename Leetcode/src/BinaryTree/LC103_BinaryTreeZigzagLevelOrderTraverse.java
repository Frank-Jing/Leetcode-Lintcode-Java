package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC103_BinaryTreeZigzagLevelOrderTraverse {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if(!reverse){
                    level.addFirst(cur.val);
                }else{
                    level.addLast(cur.val);
                }

                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            ans.add(level);
            reverse = !reverse;
        }

        return ans;
    }
}
