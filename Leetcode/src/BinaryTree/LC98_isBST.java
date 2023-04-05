package Leetcode.src.BinaryTree;

public class LC98_isBST {
    class Solution_recursion {

        public boolean isValidBST(TreeNode root) {
            if(root.left == null && root.right == null) return true;
            Info ans = helper(root);
            return ans.isBST;
        }

        public Info helper(TreeNode head){
            if(head == null) return new Info(Long.MAX_VALUE, Long.MIN_VALUE, true);

            Info leftInfo = helper(head.left);
            Info rightInfo = helper(head.right);

            boolean isBST = leftInfo.isBST && rightInfo.isBST;
            if(leftInfo.max >= head.val || rightInfo.min <= head.val){
                isBST = false;
            }

            // add this to increase speed, can ignore this part
            if(!isBST){
                return new Info(Long.MIN_VALUE, Long.MAX_VALUE, false);
            }

            Long treeMin = Math.min(head.val, Math.min(leftInfo.min, rightInfo.min));
            Long treeMax = Math.max(head.val, Math.max(leftInfo.max, rightInfo.max));

            return new Info(treeMin, treeMax, isBST);
        }

        class Info{
            Long min;
            Long max;
            boolean isBST;

            Info(Long treeMin, Long treeMax, boolean validBst){
                this.min = treeMin;
                this.max = treeMax;
                this.isBST = validBst;
            }
        }
    }

    class Solution_Morris {
        public boolean isValidBST(TreeNode head) {
            if (head == null) {
                return true;
            }
            TreeNode cur = head;
            TreeNode mostRight = null;
            Integer pre = null;
            boolean ans = true;
            while (cur != null) {
                mostRight = cur.left;
                if (mostRight != null) {
                    while (mostRight.right != null && mostRight.right != cur) {
                        mostRight = mostRight.right;
                    }
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        cur = cur.left;
                        continue;
                    } else {
                        mostRight.right = null;
                    }
                }
                if (pre != null && pre >= cur.val) {
                    ans = false;
                }
                pre = cur.val;
                cur = cur.right;
            }
            return ans;
        }
    }

    class Solution_GlobalPointer {
        TreeNode pre;

        public boolean isValidBST(TreeNode root) {
            if(root.left == null && root.right == null) return true;

            return inOrder(root);
        }

        private boolean inOrder(TreeNode cur) {
            if(cur == null) return true;

            if (!inOrder(cur.left)) return false;

            if(pre != null && pre.val >= cur.val) return false;

            pre = cur;

            return inOrder(cur.right);
        }
    }

}
