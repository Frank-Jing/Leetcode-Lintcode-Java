package Leetcode.src.BinaryTree;

public class LC250_UnivaleSubtreeCount {
    class Solution {
        int cnt = 0;
        public int countUnivalSubtrees(TreeNode root) {
            process(root);
            return cnt;
        }

        public boolean process(TreeNode head){
            if(head == null) return true;
            if(head.left == null && head.right == null){
                cnt++;
                return true;
            }

            boolean leftUni = process(head.left);
            boolean rightUni = process(head.right);

            boolean headUni = false;
            if(head.left != null && head.right != null){
                if(leftUni && rightUni && head.val == head.left.val && head.val == head.right.val){
                    cnt++;
                    headUni = true;
                }
            }else if(head.left == null){
                if(rightUni && head.val == head.right.val){
                    cnt++;
                    headUni = true;
                }
            }else if(head.right == null){
                if(leftUni && head.val == head.left.val){
                    cnt++;
                    headUni = true;
                }
            }

            return headUni;
        }
    }
}
