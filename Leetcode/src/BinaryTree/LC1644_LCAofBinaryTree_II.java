package Leetcode.src.BinaryTree;

public class LC1644_LCAofBinaryTree_II {
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

    class Solution_recursive_2 {
        TreeNode res = null;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            hasPorQ(root, p, q);
            return res;
        }

        public boolean hasPorQ(TreeNode head, TreeNode p, TreeNode q){
            if(head == null) return false;
            if(head.left == null && head.right == null){
                return (head.val == p.val || head.val == q.val);
            }

            boolean leftHasPorQ = hasPorQ(head.left, p, q);
            boolean headisPorQ = (head.val == p.val || head.val == q.val);
            boolean rightHasPorQ = hasPorQ(head.right, p, q);
            //head and left must has one of p or q
            if(headisPorQ && (leftHasPorQ || rightHasPorQ)){
                res = head;
            }else if(leftHasPorQ && rightHasPorQ){
                res = head;
            }

            return (headisPorQ || leftHasPorQ || rightHasPorQ);
        }
    }
}
