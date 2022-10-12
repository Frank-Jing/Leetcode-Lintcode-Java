package Leetcode.src.BinaryTree;

public class LC700_SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode ans = null;
        if(root != null){
            if(root.val == val){
                ans = root;
            }else if(root.val < val){
                ans = searchBST(root.right, val);
            }else{
                ans = searchBST(root.left, val);
            }
        }

        return ans;
    }

    public TreeNode searchBST_iterative(TreeNode root, int val) {
        TreeNode ans = null;
        while(root != null){
            if(root.val == val) break;
            root = root.val > val? root.left : root.right;
        }
        return root;
    }
}
