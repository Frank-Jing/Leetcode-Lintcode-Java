package Leetcode.src.BinaryTree;

public class LC236_LCAofBinaryTree {
    class Solution_recursive {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return process(root, p, q).LCA;
        }

        class Info {
            boolean findP;
            boolean findQ;
            TreeNode LCA;

            Info (boolean foundP, boolean foundQ, TreeNode ancestor){
                this.findP = foundP;
                this.findQ = foundQ;
                this.LCA = ancestor;
            }
        }

        public Info process(TreeNode head, TreeNode p, TreeNode q){
            if(head == null) return new Info(false, false, null);

            Info leftInfo = process(head.left, p, q);
            Info rightInfo = process(head.right, p, q);

            boolean findP = head.val == p.val || leftInfo.findP || rightInfo.findP;
            boolean findQ = head.val == q.val || leftInfo.findQ || rightInfo.findQ;
            TreeNode ancestor = null;
            if(leftInfo.LCA != null){
                ancestor = leftInfo.LCA;
            }else if(rightInfo.LCA != null){
                ancestor = rightInfo.LCA;
            }else{
                if(findP && findQ){
                    ancestor = head;
                }
            }

            return new Info(findP, findQ, ancestor);
        }
    }
}
