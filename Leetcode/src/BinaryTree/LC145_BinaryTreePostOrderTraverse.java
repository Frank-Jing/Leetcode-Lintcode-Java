package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC145_BinaryTreePostOrderTraverse {
    public List<Integer> postorderTraversal_twoStack(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        //get head - right - left (change order of stack push for PreOrder Traverse)
        if(root != null){
            Deque<TreeNode> stack = new ArrayDeque<>();
            Deque<Integer> ansStack = new ArrayDeque<>();
            stack.push(root);
            while(!stack.isEmpty()){
                TreeNode cur = stack.pop();
                ansStack.push(cur.val);
                if(cur.left != null){
                    stack.push(cur.left);
                }
                if(cur.right != null){
                    stack.push(cur.right);
                }
            }
            //get left - right - head (reverse of the stack 1)
            while(!ansStack.isEmpty()){
                ans.add(ansStack.pop());
            }
        }

        return ans;
    }

    //use visited node ptr to check if left right leaf has been visited
    //equivalent to truncating the leaf each time
    public List<Integer> postorderTraversal_singleStack(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if(root != null){
            Deque<TreeNode> stack = new ArrayDeque<>();
            TreeNode endFlag = root;
            stack.push(root);
            while(!stack.isEmpty()){
                TreeNode c = stack.peek();
                if(c.left != null && c.left != endFlag && c.right != endFlag){
                    stack.push(c.left);
                }else if(c.right != null && c.right != endFlag){
                    stack.push(c.right);
                }else{
                    endFlag = stack.pop();
                    ans.add(endFlag.val);
                }
            }
        }

        return ans;
    }
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if(root == null) return res;

        res.addAll(postorderTraversal2(root.left));
        res.addAll(postorderTraversal2(root.right));
        res.add(root.val);

        return res;
    }
}
