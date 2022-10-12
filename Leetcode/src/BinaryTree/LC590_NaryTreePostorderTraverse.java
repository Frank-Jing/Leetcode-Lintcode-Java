package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC590_NaryTreePostorderTraverse {
    class Solution_recursive {
        public List<Integer> postorder(Node root) {
            List<Integer> ans = new LinkedList<>();
            if(root == null) return ans;

            helper(root, ans);
            return ans;
        }

        public void helper(Node head, List<Integer> list){
            if(head.children == null){
                list.add(head.val);
            }else{
                for(Node child : head.children){
                    helper(child, list);
                }
                list.add(head.val);
            }
        }
    }

    class Solution_2stack {
        public List<Integer> postorder(Node root) {
            List<Integer> ans = new LinkedList<>();
            if(root == null) return ans;

            Deque<Node> stack1 = new ArrayDeque<>();
            Deque<Node> stack2 = new ArrayDeque<>();
            //the stack2 could be removed for an advanced soln
            //since ans is a LinkedList, we could use addFirst
            stack1.push(root);
            while(!stack1.isEmpty()){
                Node cur = stack1.pop();
                stack2.push(cur);
                if(cur.children != null){
                    for(Node child: cur.children){
                        stack1.push(child);
                    }
                }
            }

            //same as LinkedList.addFirst()
            while(!stack2.isEmpty()){
                Node cur = stack2.pop();
                ans.add(cur.val);
            }

            return ans;
        }
    }

    class Solution_1stack {
        public List<Integer> postorder(Node root) {
            LinkedList<Integer> ans = new LinkedList<>();
            if(root == null) return ans;

            Deque<Node> stack1 = new ArrayDeque<>();
            // Deque<Node> stack2 = new ArrayDeque<>();
            stack1.push(root);
            while(!stack1.isEmpty()){
                Node cur = stack1.pop();
                ans.addFirst(cur.val);
                if(cur.children != null){
                    for(Node child: cur.children){
                        stack1.push(child);
                    }
                }
            }

            // while(!stack2.isEmpty()){
            //     Node cur = stack2.pop();
            //     ans.add(cur.val);
            // }

            return ans;
        }
    }
}
