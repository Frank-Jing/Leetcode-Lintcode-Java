package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC107_BinaryTreeLevelOrderTraverse_II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                level.add(cur.val);

                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            ans.addFirst(level);
        }

        return ans;

    }
}
