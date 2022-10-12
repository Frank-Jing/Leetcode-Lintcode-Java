package Leetcode.src.BinaryTree;

public class LC333_MaxBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        return process(root).maxBSTSize;
    }

    class Info{
        int maxBSTSize;
        int min;
        int max;
        int allSize;
        Info(int maxBSTSubTreeSize, int min, int max, int allSize){
            this.maxBSTSize = maxBSTSubTreeSize;
            this.min = min;
            this.max = max;
            this.allSize = allSize;
        }
    }

    public Info process(TreeNode head){
        if(head == null) return new Info(0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        Info left = process(head.left);
        Info right = process(head.right);

        int min = Math.min(head.val, Math.min(left.min, right.min));
        int max = Math.max(head.val, Math.max(left.max, right.max));
        int allSize = left.allSize + 1 + right.allSize;

        int maxBSTSize = Math.max(left.maxBSTSize, right.maxBSTSize);
        //judge if left or right subTree is a BST
        if(head.val > left.max && head.val < right.min){
            if(left.allSize == left.maxBSTSize && right.allSize == right.maxBSTSize){
                maxBSTSize = allSize;
            }
        }

        return new Info(maxBSTSize, min, max, allSize);
    }
}
