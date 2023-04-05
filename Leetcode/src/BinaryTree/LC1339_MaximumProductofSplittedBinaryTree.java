package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class LC1339_MaximumProductofSplittedBinaryTree {
    // O(N)
    class Solution {
        public int maxProduct(TreeNode root) {
            List<Integer> values = new ArrayList<>();
            long total = process(root, values);

            long mod = 1000000007;
            long ans = 0;
            for(int subTreeSum : values){
                long product = (total - subTreeSum)*subTreeSum;
                ans = Math.max(product, ans);
            }

            return (int)(ans%mod);
        }

        public int process(TreeNode root, List<Integer> arr){
            if(root == null) return 0;

            int leftSum = process(root.left, arr);
            int rightSum = process(root.right, arr);
            int subTreeSum = leftSum + rightSum + root.val;
            arr.add(subTreeSum);

            return subTreeSum;
        }
    }
}
