package Leetcode.src.BinaryTree;

public class LC2385_InfectTimeFromStart {
    class Solution {
        int maxDist = 0;
        public int amountOfTime(TreeNode root, int start) {
            process(root, start);
            return maxDist;
        }

        public int process(TreeNode node, int start){
            if(node == null){
                return 0;
            }
            int depth = 0;
            int lDepth = process(node.left, start);
            int rDepth = process(node.right, start);
            if(node.val == start){
                // in the recursion, when infection start is first met, record its depth
                maxDist = Math.max(lDepth, rDepth);
                depth = -1; // parent of infection start's distance is 1
                // vice versa, parent's of parent to infection start has dist 2
                // negative sign is a mark for branch with infection start
            }else if (lDepth >= 0 && rDepth >= 0){
                // this means these 2 branches has no infection start
                depth = Math.max(lDepth, rDepth) + 1;
            }else{
                // key point
                int dist2Start = Math.abs(lDepth) + Math.abs(rDepth);
                maxDist = Math.max(maxDist, dist2Start);
                depth = Math.min(lDepth, rDepth) - 1;
            }

            return depth;
        }
    }
}
