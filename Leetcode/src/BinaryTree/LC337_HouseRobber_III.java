package Leetcode.src.BinaryTree;

public class LC337_HouseRobber_III {
    class Solution {
        public int rob(TreeNode root) {
            Info begin = decide(root);
            return Math.max(begin.go, begin.avoid);
        }

        class Info {
            int go;
            int avoid;
            Info(int go, int avoid){
                this.go = go;
                this.avoid = avoid;
            }
        }

        public Info decide(TreeNode head){
            if(head == null) return new Info(0,0);

            Info leftGain = decide(head.left);
            Info rightGain = decide(head.right);

            int go = head.val;
            int avoid = 0;

            go += leftGain.avoid + rightGain.avoid;
            avoid += Math.max(leftGain.go, leftGain.avoid) + Math.max(rightGain.go, rightGain.avoid);

            return new Info(go, avoid);
        }
    }
}
