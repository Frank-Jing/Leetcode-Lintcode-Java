package Leetcode.src;

import java.util.Stack;

public class LC426_ConvertBSTToSortedDoubleLinkedList {
    class Solution {

        public TreeNode treeToDoublyList(TreeNode root) {
            if(root == null) return null;

            Stack<TreeNode> stack = new Stack<>();
            TreeNode head = new TreeNode();

            while(root != null){
                stack.push(root);
                root = root.left;
            }

            TreeNode ptr = head;
            while(!stack.isEmpty()){
                TreeNode cur = stack.pop();
                ptr = joinNodes(ptr, cur);

                if(cur.right != null){
                    cur = cur.right;
                    while(cur != null){
                        stack.push(cur);
                        cur = cur.left;
                    }
                }
            }

            head.left = null;
            ptr.right = head.right;
            head.right.left = ptr;

            return head.right;

        }

        public TreeNode joinNodes(TreeNode pre, TreeNode next){
            pre.right = next;
            next.left = pre;
            return next;
        }

    }
    class Solution2{
        public TreeNode treeToDoublyList(TreeNode root) {
            if(root == null) return null;
            TreeNode head = divideAndConnect(root);
            TreeNode tail = head;

            while(tail.right != null){
                tail = tail.right;
            }

            tail.right = head;
            head.left = tail;

            return head;
        }

        public TreeNode divideAndConnect(TreeNode root){
            if(root == null) return null;
            TreeNode left = divideAndConnect(root.left);
            TreeNode right = divideAndConnect(root.right);
            root.left = null; //could remove and increase speed
            root.right = null; // could remove and increse speed
            return merge(left, root, right);
        }

        public TreeNode merge(TreeNode left, TreeNode root, TreeNode right){
            root.right = right;
            if(right != null){
                right.left = root;
            }
            if(left != null){
                TreeNode head = left;
                while(left.right != null){
                    left = left.right;
                }
                left.right = root;
                root.left = left;

                return head;
            }
            return root;
        }
    }

}



