package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.List;

public class LC257_BinaryTreePaths {
    List<String> ans = new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        String prePath = "";
        process(root, prePath);
        return ans;
    }

    private void process(TreeNode head, String prePath){
        if(head == null) return;

        prePath += String.valueOf(head.val);
        if(head.left == null && head.right == null){
            ans.add(prePath);
            return;
        }
        prePath += "->";
        process(head.left, prePath);
        process(head.right, prePath);
    }
}
