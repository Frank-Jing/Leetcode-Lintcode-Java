package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC589_NaryTreePreorderTraverse {
    class Solution {
        public List<Integer> preorder(Node root) {
            Deque<Node> stack = new ArrayDeque<>();
            List<Integer> ans = new LinkedList<>();

            if(root != null){
                stack.push(root);
                while(!stack.isEmpty()){
                    Node cur = stack.pop();
                    ans.add(cur.val);
                    if(cur.children != null){
                        int size = cur.children.size();
                        for(int i = size - 1; i>=0; i--){
                            stack.push(cur.children.get(i));
                        }
                    }
                }
            }

            return ans;
        }
    }

    class Solution_recursive {
        public List<Integer> preorder(Node root) {
            List<Integer> ans = new LinkedList<>();
            preorder(root, ans);
            return ans;
        }

        public void preorder(Node head, List<Integer> res){
            if(head != null){
                res.add(head.val);
                for(Node child : head.children){
                    preorder(child, res);
                }
            }
        }
    }
}
