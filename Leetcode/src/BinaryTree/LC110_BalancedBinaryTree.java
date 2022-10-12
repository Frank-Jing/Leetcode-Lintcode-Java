package Leetcode.src.BinaryTree;

public class LC110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        Info res = helper(root);
        return res.balanced;
    }

    public Info helper(TreeNode head){
        if(head == null) return new Info(0, true);
        Info leftRes = helper(head.left);
        Info rightRes = helper(head.right);

        int height = Math.max(leftRes.height, rightRes.height) + 1;
        boolean balanced = true;
        if(Math.abs(leftRes.height - rightRes.height) > 1){
            balanced = false;
        }
        if(!leftRes.balanced || !rightRes.balanced){
            balanced = false;
        }
        return new Info(height, balanced);
    }

    class Info{
        int height;
        boolean balanced;
        Info(int h, boolean balanced){
            this.height = h;
            this.balanced = balanced;
        }
    }
}
