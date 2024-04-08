package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LC314_BinaryTreeVerticalOrderTraverse {
    class Solution {
        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();

            if(root == null){
                return res;
            }

            int minCol = 0;
            int maxCol = 0;

            Map<Integer, List<Integer>> colMap = new HashMap<>();
            Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
            q.offer(new Pair(root, 0));

            while(!q.isEmpty()){
                Pair<TreeNode, Integer> p = q.poll();
                TreeNode node = p.getKey();
                int col = p.getValue();

                if(!colMap.containsKey(col)){
                    colMap.put(col, new ArrayList<Integer>());
                }
                colMap.get(col).add(node.val);
                minCol = Math.min(minCol, col);
                maxCol = Math.max(maxCol, col);
                
                if(node.left != null){
                    q.offer(new Pair(node.left, col - 1));
                }
                if(node.right != null){
                    q.offer(new Pair(node.right, col + 1));
                }    
            }

            for(int i = minCol; i <= maxCol; i++){
                res.add(colMap.get(i));
            }
            
            return res;
        }
    }

    class FAILED_Solution {
        //this soln can't resolve the order related with level
        //If two nodes are in the same row and column, the order should be from left to right.
        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            if(root == null) return null;
            LinkedList<LinkedList<Integer>> ans = process(root).nodes;
            for(int i = 0; i<ans.size(); i++){
                res.add(ans.get(i));
            }
            return res;
        }

        class Info {
            int rootPos;
            LinkedList<LinkedList<Integer>> nodes;
            Info(int pos, LinkedList<LinkedList<Integer>> list){
                this.rootPos = pos;
                this.nodes = list;
            }
        }

        public Info process(TreeNode head){
            LinkedList<LinkedList<Integer>> ans = new LinkedList<>();
            if(head == null){
                return new Info(0, ans);
            }
            if(head.left == null && head.right == null){
                LinkedList<Integer> node = new LinkedList<>();
                node.add(head.val);
                ans.add(node);
                return new Info(0, ans);
            }

            Info left = process(head.left);
            Info right = process(head.right);

            int headPos = 0;

            if(left.nodes.size() == 0){
                ans = right.nodes;
                if(right.rootPos == 0){
                    LinkedList<Integer> node = new LinkedList<>();
                    node.add(head.val);
                    ans.addFirst(node);
                }else{
                    ans.get(right.rootPos - 1).addFirst(head.val);
                    headPos = right.rootPos - 1;
                }
            }else if(right.nodes.size() == 0){
                ans = left.nodes;
                if(left.rootPos == left.nodes.size() - 1){
                    LinkedList<Integer> node = new LinkedList<>();
                    node.add(head.val);
                    ans.addLast(node);
                    headPos = ans.size() - 1;
                }else{
                    ans.get(left.rootPos + 1).addFirst(head.val);
                    headPos = left.rootPos + 1;
                }
            }else{
                ans = left.nodes;
                if(left.rootPos == ans.size() - 1){
                    LinkedList<Integer> node = new LinkedList<>();
                    node.add(head.val);
                    ans.addLast(node);
                    headPos = ans.size() - 1;
                }else{
                    ans.get(left.rootPos + 1).addFirst(head.val);
                    headPos = left.rootPos + 1;
                }

                if(right.rootPos == 0){
                    ans.addAll(right.nodes);
                }else{
                    int ind = headPos - right.rootPos + 1;
                    for(int i = 0; i<right.nodes.size(); i++){
                        if(ind + i < ans.size()){
                            ans.get(ind + i).addAll(right.nodes.get(i));
                        }else{
                            ans.add(right.nodes.get(i));
                        }
                    }
                }
            }

            return new Info(headPos, ans);

        }
    }
}
