package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC230_BSTKthSmallestElement {
    class Solution_stackIterate {
        // iterative inorder traverse
        public int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> stack = new ArrayDeque<>();
            int cnt = 0;
            while(!stack.isEmpty() || root != null){
                if(root != null){
                    stack.push(root);
                    root = root.left;
                }else{
                    root = stack.pop();
                    cnt++;
                    if(cnt == k){
                        return root.val;
                    }
                    root = root.right;
                }

            }

            return 0;
        }
    }

    class Solution_recursive {
        int cnt = 0;
        TreeNode target;
        public int kthSmallest(TreeNode root, int k) {
            inorderProcess(root, k);
            return target.val;
        }

        public void inorderProcess(TreeNode head, int k){
            if(head == null) return;
            inorderProcess(head.left, k);

            this.cnt++;
            if(cnt == k) {
                this.target = head;
                return;
            }

            inorderProcess(head.right, k);
        }
    }
}
