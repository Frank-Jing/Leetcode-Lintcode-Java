package Leetcode.src.BinaryTree;

public class LC226_InvertBinaryTree {
    class Solution_recursive {
        public TreeNode invertTree(TreeNode root) {
            if(root == null) return null;
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            root.left = right;
            root.right = left;

            return root;
        }
    }
}
