package Leetcode.src.BinaryTree;

public class LC235_LCAofBST {
    class Solution_recursive {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if(p.val > q.val){
                TreeNode tmp = p;
                p = q;
                q = tmp;
            }

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

            if(q.val < head.val){
                return process(head.left, p, q);
            }else if(p.val > head.val){
                return process(head.right, p, q);
            }

            return new Info(true, true, head);
        }
    }
}
