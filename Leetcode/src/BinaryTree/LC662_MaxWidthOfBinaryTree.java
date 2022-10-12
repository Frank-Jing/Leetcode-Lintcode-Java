package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class LC662_MaxWidthOfBinaryTree {
    class NodePos{
        TreeNode node;
        int pos;
        NodePos(TreeNode head, int posInd){
            this.node = head;
            this.pos = posInd;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        int max = 0;
        Queue<NodePos> queue = new LinkedList<>();
        queue.offer(new NodePos(root, 0));

        while(!queue.isEmpty()){
            int size = queue.size();
            int startInd = 0, endInd = 0;
            for(int i = 0; i<size; i++){
                NodePos cur = queue.poll();
                int curPos = cur.pos;
                TreeNode curNode = cur.node;
                if(i == 0) startInd = curPos;
                if(i == size - 1){
                    endInd = curPos;
                    max = Math.max(max, endInd - startInd + 1);
                }
                if(curNode.left != null){
                    queue.offer(new NodePos(curNode.left, curPos * 2 + 1));
                }
                if(curNode.right != null){
                    queue.offer(new NodePos(curNode.right, curPos * 2 + 2));
                }
            }

        }

        return max;
    }
}
