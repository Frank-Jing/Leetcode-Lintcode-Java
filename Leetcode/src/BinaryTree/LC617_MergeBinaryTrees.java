package Leetcode.src.BinaryTree;

public class LC617_MergeBinaryTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode head = new TreeNode();
        if(root1 == null){
            return root2;
        }else if(root2 == null){
            return root1;
        }else{
            head.val = root1.val + root2.val;
            head.left = mergeTrees(root1.left, root2.left);
            head.right = mergeTrees(root1.right, root2.right);
        }

        return head;
    }
}
