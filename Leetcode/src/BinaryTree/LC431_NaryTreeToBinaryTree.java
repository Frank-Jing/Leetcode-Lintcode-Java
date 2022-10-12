package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.List;

public class LC431_NaryTreeToBinaryTree {
    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if(root == null) return null;
            TreeNode head = helpEncode(root, root.children);
            return head;
        }

        public TreeNode helpEncode(Node root, List<Node> children){
            if(root == null) return null;
            TreeNode head = new TreeNode(root.val);
            if(children == null) return head;

            TreeNode tNode = null;

            for(Node child:children){
                TreeNode cur = helpEncode(child, child.children);
                if(tNode == null){
                    tNode = cur;
                    head.left = tNode;
                }else{
                    tNode.right = cur;
                    tNode = tNode.right;
                }
            }

            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if(root == null) return null;
            return new Node(root.val, helpDecode(root.left));
        }

        public List<Node> helpDecode(TreeNode head){
            List<Node> children = new LinkedList<>();
            while(head != null){
                Node cur = new Node(head.val, helpDecode(head.left));
                children.add(cur);
                head = head.right;
            }
            return children;
        }
    }
}
