package Leetcode.src.BinaryTree;

public class LC124_BinaryTreeMaxPathSum {
    class Solution {
        int maxPath = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            if(root.left == null && root.right == null) return root.val;
            Info rootPath = process(root);
            int pathViaRootMax = rootPath.leftMax + rootPath.rightMax + root.val;
            maxPath = Math.max(maxPath, pathViaRootMax);
            return maxPath;
        }

        public class Info{
            int leftMax;
            int rightMax;

            Info (int m1, int m2){
                this.leftMax = m1;
                this.rightMax = m2;
            }
        }

        public Info process(TreeNode head){
            if(head == null) return new Info(0,0);
            // if(head.left == null && head.right == null) return new Info(0, 0);

            Info leftInfo = process(head.left);
            Info rightInfo = process(head.right);

            if(leftInfo.leftMax < 0){
                leftInfo.leftMax = 0;
            }
            if(leftInfo.rightMax < 0){
                leftInfo.rightMax = 0;
            }
            int leftMax = 0;
            int leftSubmax = Integer.MIN_VALUE;
            if(head.left != null){
                leftMax = Math.max(leftInfo.leftMax, leftInfo.rightMax) + head.left.val;
                leftMax = Math.max(leftMax, 0);
                leftSubmax = leftInfo.leftMax + leftInfo.rightMax + head.left.val;
            }

            if(rightInfo.leftMax < 0){
                rightInfo.leftMax = 0;
            }
            if(rightInfo.rightMax < 0){
                rightInfo.rightMax = 0;
            }

            int rightMax = 0;
            int rightSubmax = Integer.MIN_VALUE;
            if(head.right != null){
                rightMax = Math.max(rightInfo.leftMax, rightInfo.rightMax) + head.right.val;
                rightMax = Math.max(rightMax, 0);
                rightSubmax = rightInfo.leftMax + rightInfo.rightMax + head.right.val;
            }

            maxPath = Math.max(maxPath, Math.max(leftSubmax, rightSubmax));

            return new Info(leftMax, rightMax);
        }

    }

    class Solution_2 {
        int maxPath = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            if(root.left == null && root.right == null) return root.val;
            process(root);
            return maxPath;
        }


        public int process(TreeNode head){
            if(head == null) return 0;
            // if(head.left == null && head.right == null) return new Info(0, 0);

            int leftSide = process(head.left);
            int rightSide = process(head.right);

            int maxOneSide = Math.max(leftSide, rightSide);
            int maxFromOneSide = Math.max(head.val, head.val + maxOneSide);
            int maxFromTwoSide = Math.max(maxFromOneSide, leftSide + rightSide + head.val);

            maxPath = Math.max(maxPath, maxFromTwoSide);

            return maxFromOneSide;
        }

    }

    class Solution_3 {
        int max =Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            helper(root);
            return max;
        }

        public int helper(TreeNode root) {
            if(root == null) return 0;
            int left = helper(root.left);
            int right = helper(root.right);
            int leftRight = Math.max(left,right);
            int withRoot = Math.max(root.val, root.val+leftRight);
            //one side or just root value if both subTree are negative
            int maxAll = Math.max(withRoot, left+right+root.val);
            max = Math.max(max,maxAll);
            return withRoot;
        }
    }

}
