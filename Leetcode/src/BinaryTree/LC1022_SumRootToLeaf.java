package Leetcode.src.BinaryTree;

public class LC1022_SumRootToLeaf {
    class Solution {
        int sum = 0;
        public int sumRootToLeaf(TreeNode root) {
            helper(root, 0);
            return sum;
        }

        public void helper(TreeNode head, int pre){
            if(head.left == null && head.right == null){
                sum += pre*2 + head.val;
            }
            pre = pre*2 + head.val;
            if(head.left != null){
                helper(head.left, pre);
            }
            if(head.right != null){
                helper(head.right, pre);
            }
        }
    }
}
