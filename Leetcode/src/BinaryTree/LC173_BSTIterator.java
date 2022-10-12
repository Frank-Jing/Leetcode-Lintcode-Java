package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC173_BSTIterator {
    //recurisve way, build list first
    class BSTIterator {
        List<Integer> array = new LinkedList<>();
        int ptr;

        public BSTIterator(TreeNode root) {
            inorderTraverse(root);
            this.ptr = 0;
        }

        public int next() {
            return this.array.get(ptr++);
        }

        public boolean hasNext() {
            return ptr < array.size();
        }

        private void inorderTraverse(TreeNode root){
            if(root == null) return;
            inorderTraverse(root.left);
            this.array.add(root.val);
            inorderTraverse(root.right);

        }
    }

    //non-recursive way, same as stack inorder traverse
    class BSTIterator_2 {
        Deque<TreeNode> stack = new ArrayDeque<>();

        public BSTIterator_2(TreeNode root) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode cur = stack.pop();
            int ans = cur.val;
            if(cur.right != null){
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
            return ans;
        }

        public boolean hasNext() {
            return stack.size() > 0;
        }
    }
}
