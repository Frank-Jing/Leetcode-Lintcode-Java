package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC199_BinaryTreeRightSideView {
    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            if(root == null) return ans;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()){
                int size = queue.size();
                for(int i = 0; i<size; i++){
                    TreeNode cur = queue.poll();
                    if(i == size - 1){
                        ans.add(cur.val);
                    }
                    if(cur.left != null) queue.offer(cur.left);
                    if(cur.right != null) queue.offer(cur.right);
                }
            }

            return ans;
        }
    }

    class Solution_recursive_merge {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ans = new LinkedList<>();
            if(root == null) return ans;

            List<Integer> leftTreeView = rightSideView(root.left);
            List<Integer> rightTreeView = rightSideView(root.right);

            ans.add(root.val);
            if(rightTreeView == null){
                if(leftTreeView != null){
                    ans.addAll(leftTreeView);
                }
            }else{
                ans.addAll(rightTreeView);
                if(leftTreeView != null && leftTreeView.size() > rightTreeView.size()){
                    for(int i = rightTreeView.size(); i<leftTreeView.size(); i++){
                        ans.add(leftTreeView.get(i));
                    }
                }
            }

            return ans;
        }
    }

    class Solution_recursive {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            rightSideResult(root, result, 0);
            return result;
        }

        private static void rightSideResult(TreeNode root, List<Integer> result, int level) {
            if (root == null) {
                return;
            }

            if(level == result.size()) {
                result.add(root.val);
            }

            //If we want the LEFT side view instead of the RIGHT side view.
        /*
            rightSideResult(root.left, result, level + 1);
            rightSideResult(root.right, result, level + 1);
        */
            rightSideResult(root.right, result, level + 1);
            rightSideResult(root.left, result, level + 1);
        }
    }
}
