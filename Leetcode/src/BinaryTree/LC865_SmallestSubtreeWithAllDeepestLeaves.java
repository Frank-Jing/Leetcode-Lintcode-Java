package Leetcode.src.BinaryTree;

public class LC865_SmallestSubtreeWithAllDeepestLeaves {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return process(root).lca;
    }

    class Info{
        int level;
        TreeNode lca;
        Info(int currentLevel, TreeNode deepLeaveParent){
            this.level = currentLevel;
            this.lca = deepLeaveParent;
        }
    }

    public Info process(TreeNode head){
        if(head == null) return new Info(0, head);

        Info left = process(head.left);
        Info right = process(head.right);

        int curLevel = Math.max(left.level, right.level) + 1;

        TreeNode deepLeaveParent = head;
        if(left.level > right.level){
            deepLeaveParent = left.lca;
        }else if(left.level < right.level){
            deepLeaveParent = right.lca;
        }

        return new Info(curLevel, deepLeaveParent);
    }
}
