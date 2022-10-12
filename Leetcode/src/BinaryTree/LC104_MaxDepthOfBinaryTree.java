package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC104_MaxDepthOfBinaryTree {
    class Solution {
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            return Math.max(left, right) + 1;
        }
    }

    class Solution_postOrder {
        public int maxDepth(TreeNode h) {
            if(h == null) return 0;

            Stack<TreeNode> stack = new Stack<>();
            stack.push(h);
            TreeNode c = null;

            int max = 0;
            int curDep = 1;

            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                    curDep++;
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                    curDep++;
                } else {
                    stack.pop();
                    max = Math.max(curDep, max);
                    curDep--;
                    h = c;
                }
            }

            return max;
        }
    }

    class Solution_BFS {
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            TreeNode nextEnd = null;
            TreeNode curEnd = root;
            int max = 0;

            while(!queue.isEmpty()){
                TreeNode cur = queue.poll();
                if(cur.left != null){
                    queue.offer(cur.left);
                    nextEnd = cur.left;
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                    nextEnd = cur.right;
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
}
