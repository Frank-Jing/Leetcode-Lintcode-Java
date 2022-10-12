package Leetcode.src.BinaryTree;

public class LC129_SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return process(root, 0);
    }

    public int process(TreeNode head, int preSum){
        if(head == null) return preSum;
        preSum = preSum*10 + head.val;
        if(head.left == null && head.right == null) return preSum;
        int left = 0, right = 0;
        if(head.left != null){
            left = process(head.left, preSum);
        }
        if(head.right != null){
            right = process(head.right, preSum);
        }
        return left + right;
    }
}
