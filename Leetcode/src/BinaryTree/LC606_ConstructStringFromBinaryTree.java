package Leetcode.src.BinaryTree;

public class LC606_ConstructStringFromBinaryTree {
    class Solution {
        public String tree2str(TreeNode t) {
            if (t == null)
                return "";
            StringBuilder sb = new StringBuilder();
            helper(t, sb);
            return sb.toString();
        }

        private void helper(TreeNode root, StringBuilder sb) {
            sb.append(root.val);
            if (root.left == null && root.right == null)
                return;
            if (root.left != null) {
                sb.append("(");
                helper(root.left, sb);
                sb.append(")");
            }
            if (root.right != null) {
                if (root.left == null) {
                    sb.append("()");
                }
                sb.append("(");
                helper(root.right, sb);
                sb.append(")");
            }
        }
    }

    class Solution2 {
        public String tree2str(TreeNode root) {
            if(root == null) return null;
            String ans = Integer.toString(root.val);
            if(root.left == null & root.right == null){
                return ans;
            }

            if(root.left != null){
                ans = ans + "(" + tree2str(root.left) + ")";
            }
            if(root.right != null){
                if(root.left == null){
                    ans = ans + "()";
                }
                ans = ans + "(" + tree2str(root.right) + ")";
            }

            return ans;
        }
    }

}
