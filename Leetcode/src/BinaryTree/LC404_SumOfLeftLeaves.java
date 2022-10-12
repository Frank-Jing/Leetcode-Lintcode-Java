package Leetcode.src.BinaryTree;

public class LC404_SumOfLeftLeaves {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root.left == null && root.right == null) return sum;
        process(root);
        return sum;
    }

    public void process(TreeNode root){
        if(root == null) return;
        if(root.left != null){
            if(root.left.left == null && root.left.right == null){
                sum += root.left.val;
            }
        }
        process(root.left);
        process(root.right);
    }
}
