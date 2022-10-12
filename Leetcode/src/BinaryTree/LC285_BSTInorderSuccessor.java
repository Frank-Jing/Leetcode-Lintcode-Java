package Leetcode.src.BinaryTree;

public class LC285_BSTInorderSuccessor {
    class Solution {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode successor = null;

            while (root != null) {
                if (p.val >= root.val) {
                    root = root.right;
                } else {
                    successor = root;
                    root = root.left;
                }
            }

            return successor;
        }
    }

    class Solution_recusive {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if(root == null) return null;

            TreeNode ans = null;
            if(root.val <= p.val){
                ans = inorderSuccessor(root.right, p);
            }else{
                TreeNode right = inorderSuccessor(root.left, p);
                ans = right == null? root: right;
            }
            return ans;
        }
    }

    class Solution_preSuccessor {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if(root == null) return null;

            TreeNode ans = null;
            if(root.val >= p.val){
                ans = inorderSuccessor(root.left, p);
            }else{
                TreeNode right = inorderSuccessor(root.right, p);
                ans = right == null? root: right;
            }
            return ans;
        }
    }
}
