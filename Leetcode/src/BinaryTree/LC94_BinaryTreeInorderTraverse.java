package Leetcode.src.BinaryTree;

import java.util.*;

public class LC94_BinaryTreeInorderTraverse {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();

        if(root != null){
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode cur = root;
            while(!stack.isEmpty() || cur != null){
                if(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }else{
                    cur = stack.pop();
                    ans.add(cur.val);
                    cur = cur.right;
                }
            }
        }

        return ans;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if(root != null){
            ans.addAll(inorderTraversal2(root.left));
            ans.add(root.val);
            ans.addAll(inorderTraversal2(root.right));
        }

        return ans;
    }
}
