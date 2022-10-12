package Leetcode.src.BinaryTree;

public class LC543_DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        NodeInfo res = helper(root);
        return res.maxDiam - 1;
    }

    public NodeInfo helper(TreeNode head){
        if(head == null) return new NodeInfo(0, 0);

        NodeInfo leftInfo = helper(head.left);
        NodeInfo rightInfo = helper(head.right);
        int leave2leave = leftInfo.height + rightInfo.height + 1;

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int max = Math.max(leave2leave, Math.max(leftInfo.maxDiam, rightInfo.maxDiam));

        return new NodeInfo(max, height);
    }

    class NodeInfo{
        int maxDiam;
        int height;
        NodeInfo(int max, int hi){
            this.maxDiam = max;
            this.height = hi;
        }
    }
}
