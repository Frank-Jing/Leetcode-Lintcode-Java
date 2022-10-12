package Leetcode.src.BinaryTree;

import java.util.*;

public class LC1302_DeepestLeavesSum {
    class Solution_BFS {
        public int deepestLeavesSum(TreeNode root) {
            if(root == null){
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int sum = 0;
            while(!queue.isEmpty()){
                int size = queue.size();
                sum = 0;
                for(int i=0; i < size; i++){
                    TreeNode cur = queue.poll();
                    sum += cur.val;
                    if(cur.left != null){
                        queue.offer(cur.left);
                    }
                    if(cur.right != null){
                        queue.offer(cur.right);
                    }
                }
            }
            return sum;
        }
    }

    class Solution_PreOrder_Iterative {
        public int deepestLeavesSum(TreeNode root) {
            if(root == null) return 0;
            Deque<Map.Entry<TreeNode, Integer>> stack = new ArrayDeque<>();
            stack.push(new AbstractMap.SimpleEntry(root, 1)); //replace Pair here as Pair is in javafx.util
            int deepSum = 0, deepLevel = 1;
            while(!stack.isEmpty()){
                Map.Entry<TreeNode, Integer> curPair = stack.pop();
                TreeNode cur = curPair.getKey();
                int curLevel = curPair.getValue();
                if(cur.left == null && cur.right == null){
                    if(curLevel > deepLevel){
                        deepSum = cur.val;
                        deepLevel = curLevel;
                    }else if(curLevel == deepLevel){
                        deepSum += cur.val;
                    }
                }else{
                    if(cur.right != null){
                        stack.push(new AbstractMap.SimpleEntry(cur.right, curLevel + 1));
                    }
                    if(cur.left != null){
                        stack.push(new AbstractMap.SimpleEntry(cur.left, curLevel + 1));
                    }
                }
            }
            return deepSum;
        }
    }

}
