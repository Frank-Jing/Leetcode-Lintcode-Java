package Leetcode.src.BinaryTree;

public class LC111_MinDepthOfBinaryTree {
    class Solution_recursive {
        public int minDepth(TreeNode root) {
            if(root == null) return 0;
            int ans = 0;
            if(root.left != null && root.right != null){
                int left = minDepth(root.left);
                int right = minDepth(root.right);
                ans = Math.min(left, right);
            }else if(root.right == null){
                ans = minDepth(root.left);
            }else if(root.left == null){
                ans = minDepth(root.right);
            }

            return ans + 1;
        }
    }
}
