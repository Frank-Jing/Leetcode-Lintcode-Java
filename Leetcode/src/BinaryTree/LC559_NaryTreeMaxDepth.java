package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class LC559_NaryTreeMaxDepth {

    public int maxDepth(Node root) {
        if(root == null) return 0;
        int max = 0;
        if(root.children != null){
            for(Node child : root.children){
                max = Math.max(max, maxDepth(child));
            }
        }
        return max + 1;
    }

    public int maxDepth2(Node root) {
        if(root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node nextEnd = null;
        Node curEnd = root;
        int max = 0;

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.children != null){
                for(Node child : cur.children){
                    queue.add(child);
                    nextEnd = child;
                }
            }
            if(cur == curEnd){
                max++;
                curEnd = nextEnd;
                nextEnd = null;
            }

        }

        return max;
    }
}
