package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC1469_FindLonelyNodes {
    //recursive version
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if(root == null) return ans;
        if(root.left == null && root.right == null) return ans;

        if(root.left != null && root.right == null){
            ans.add(root.left.val);
        }
        if(root.left == null && root.right != null){
            ans.add(root.right.val);
        }
        List<Integer> left = getLonelyNodes(root.left);
        List<Integer> right = getLonelyNodes(root.right);

        if(left != null){
            ans.addAll(left);
        }
        if(right != null){
            ans.addAll(right);
        }

        return ans;
    }

    public List<Integer> getLonelyNodes_BFS(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if(root == null) return ans;
        if(root.left == null && root.right == null) return ans;

        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);
        while(!bfs.isEmpty()){
            TreeNode cur = bfs.poll();
            if(cur.left != null){
                bfs.add(cur.left);
                if(cur.right == null){
                    ans.add(cur.left.val);
                }
            }
            if(cur.right != null){
                bfs.add(cur.right);
                if(cur.left == null){
                    ans.add(cur.right.val);
                }
            }
        }

        return ans;
    }

    public List<Integer> getLonelyNodes2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;

    }

    private void helper(TreeNode node, List<Integer> res) {
        if (node == null) return;

        if (node.left == null && node.right != null) {
            res.add(node.right.val);
        } else if (node.left != null && node.right == null) {
            res.add(node.left.val);
        }

        helper(node.left, res);
        helper(node.right, res);
    }
}
