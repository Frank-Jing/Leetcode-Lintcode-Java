package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC938_RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null){
            return 0;
        }
        int sum = 0;

        if(root.val >= low && root.val <= high){
            sum += root.val;
        }
        //use BST character,
        // right tree is always bigger than root
        // left tree is always smaller than root
        //recursion stops at certain child node out of range
        if(root.val >= low){
            sum += rangeSumBST(root.left, low, high);
        }
        if(root.val <= high){
            sum += rangeSumBST(root.right, low, high);
        }

        return sum;
    }

    //Iterative Inorder Traverse version
    public int rangeSumBST2(TreeNode root, int low, int high) {
        if(root == null){
            return 0;
        }
        int sum = 0;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if(cur.val >= low && cur.val <= high){
                sum += cur.val;
            }
            if(cur.left != null && cur.val >= low){
                stack.push(cur.left);
            }
            if(cur.right != null && cur.val <= high){
                stack.push(cur.right);
            }
        }

        return sum;
    }
}
