package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// similar to LC114 - Flatten Binary Tree to Linked List
//method similar to LC234 - Palindrome Linked List
public class LC897_IncreasingOrderBST {
    class Solution_inorder_with_storage {
        public TreeNode increasingBST(TreeNode root) {
            if(root == null) return null;

            List<Integer> inorderList = new ArrayList<>();
            inorder(root, inorderList);

            TreeNode ans = new TreeNode(0), cur = ans;
            for(int i:inorderList){
                cur.right = new TreeNode(i);
                cur = cur.right;
            }

            return ans.right;
        }

        public void inorder(TreeNode head, List<Integer> nums){
            if(head == null) return;
            inorder(head.left, nums);
            nums.add(head.val);
            inorder(head.right, nums);
        }
    }

    class Solution_iterative {

        public TreeNode increasingBST(TreeNode root) {
            if(root == null) return null;
            TreeNode ans = new TreeNode(0);
            TreeNode toAdd;
            TreeNode cur = root;
            toAdd = ans;
            Stack<TreeNode> stack = new Stack<>();

            while(!stack.isEmpty() || cur != null){
                if(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }else{
                    cur = stack.pop();
                    cur.left = null;
                    toAdd.right = cur;
                    toAdd = cur;
                    cur = cur.right;
                }
            }

            return ans.right;
        }

    }

    class Solution_ptr_recursive {
        TreeNode cur;
        public TreeNode increasingBST(TreeNode root) {
            if(root == null) return null;
            TreeNode ans = new TreeNode(0);
            cur = ans;
            inorder(root);

            return ans.right;
        }

        public void inorder(TreeNode head){
            if(head == null) return;
            inorder(head.left);
            head.left = null;
            cur.right = head;
            cur = head;
            inorder(head.right);
        }
    }
}
