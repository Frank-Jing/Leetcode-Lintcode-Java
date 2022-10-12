package Leetcode.src.BinaryTree;

public class LC98_isBST {
    class Solution {

        public boolean isValidBST(TreeNode root) {
            if(root.left == null && root.right == null) return true;
            Info ans = helper(root);
            return ans.isBST;
        }

        public Info helper(TreeNode head){
            if(head == null) return new Info(Long.MAX_VALUE, Long.MIN_VALUE, true);

            Info leftInfo = helper(head.left);
            Info rightInfo = helper(head.right);

            boolean isBST = true;
            if(!leftInfo.isBST || !rightInfo.isBST){
                isBST = false;
            }
            if(leftInfo.max >= head.val || rightInfo.min <= head.val){
                isBST = false;
            }

            Long treeMin = Math.min(head.val, Math.min(leftInfo.min, rightInfo.min));
            Long treeMax = Math.max(head.val, Math.max(leftInfo.max, rightInfo.max));

            return new Info(treeMin, treeMax, isBST);
        }

        class Info{
            Long min;
            Long max;
            boolean isBST;

            Info(Long treeMin, Long treeMax, boolean validBst){
                this.min = treeMin;
                this.max = treeMax;
                this.isBST = validBst;
            }
        }
    }
}
