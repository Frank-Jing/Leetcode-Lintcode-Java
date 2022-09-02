package Leetcode.src;

public class LC114_FlattenBinaryTreeToLinkedList {
    class solution{
        public void flatten(TreeNode root) {
            if(root == null) return;

            //step1: move right branch to the most right leaf of left branch
            //step2:  move the left new tree to the right branch
            //step3: move the head to the next fork where there is a left branch
            TreeNode fork = root;
            while(fork != null){
                if(fork.left != null){
                    TreeNode rightMost = fork.left;
                    while(rightMost.right != null){
                        rightMost = rightMost.right;
                    }

                    rightMost.right = fork.right;
                    fork.right = fork.left;
                    fork.left = null;
                }
                fork = fork.right;
            }
        }
    }

    class soluntion2{
        public void flatten(TreeNode root) {
            root = helper(root);
        }

        public TreeNode helper(TreeNode node){
            if(node == null) return null;
            if(node.left == null && node.right == null) return node;
            TreeNode left = helper(node.left);
            TreeNode right = helper(node.right);
            return merge(left, node, right);
        }

        public TreeNode merge(TreeNode left, TreeNode root, TreeNode right){
            root.left = null;
            root.right = null; //avoid loop

            if(left != null){
                root.right = left;
            }
            if(right != null){
                TreeNode ptr = root;
                while(ptr.right != null){
                    ptr = ptr.right;
                }
                ptr.right = right;
            }

            return root;
        }
    }

}
