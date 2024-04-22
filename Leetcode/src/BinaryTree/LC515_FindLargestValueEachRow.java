package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC515_FindLargestValueEachRow {
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        if(root == null) return res;

        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();
            int levelMax = Integer.MIN_VALUE;
            for(int i=0; i< size; i++){
                TreeNode cur = q.poll();
                levelMax = Math.max(levelMax, cur.val);
                if(cur.left != null) q.offer(cur.left);
                if(cur.right != null) q.offer(cur.right);
            }
            res.add(levelMax);
        }

        return res;
    }
}
