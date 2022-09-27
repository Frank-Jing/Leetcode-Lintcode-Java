package Leetcode.src.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC1379_FindTargetInClonedTree {
    public final TreeNode getTargetCopy_iter(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        Deque<TreeNode> stackCln = new ArrayDeque<>();
        TreeNode hCln = cloned;

        while(!stackCln.isEmpty() || hCln != null){
            if(hCln!= null){
                stackCln.push(hCln);
                hCln = hCln.left;
            }else{
                hCln = stackCln.pop();
                if(hCln.val == target.val){
                    return hCln;
                }
                hCln = hCln.right;
            }
        }

        return null;
    }

    //recursion version (fast)
    //because for inOrderTraverse above (DFS), it would directly go down to the very left
    //here the only improve is that if target met, return (it would also go down to leaf in worst case)
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        if(original==null) return null;
        if(original==target) return cloned;
        if(original.left!=null){
            TreeNode node= getTargetCopy(original.left, cloned.left, target);
            if(node!=null) return node;
        }
        if(original.right!=null){
            TreeNode node= getTargetCopy(original.right, cloned.right, target);
            if(node!=null) return node;
        }
        return null;
    }
}
