package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC144_BinaryTreePreorderTraverse {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if(root != null){
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);
            while(!stack.isEmpty()){
                TreeNode cur = stack.pop();
                ans.add(cur.val);
                if(cur.right != null){
                    stack.push(cur.right);
                }
                if(cur.left != null){
                    stack.push(cur.left);
                }
            }
        }

        return ans;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if(root != null){
            ans.add(root.val);
            ans.addAll(preorderTraversal2(root.left));
            ans.addAll(preorderTraversal2(root.right));
        }
        return ans;
    }
}
