package Leetcode.src.BinaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LC894_AllPossibleFullBinaryTrees {
    class Solution {
        Map<Integer, List<TreeNode>> cache = new HashMap<>();

        public List<TreeNode> allPossibleFBT(int n) {
            if (!cache.containsKey(n)) {
                List<TreeNode> ans = new LinkedList<>();
                if (n == 1) {
                    TreeNode tree = new TreeNode(0);
                    ans.add(tree);
                    cache.put(1, ans);
                } else if (n % 2 == 1) {
                    int leftTreeCnt = 1; //initial left tree must have 1 node
                    while (n - 1 - leftTreeCnt > 0) {
                        List<TreeNode> leftTrees = allPossibleFBT(leftTreeCnt);
                        List<TreeNode> rightTrees = allPossibleFBT(n - 1 - leftTreeCnt);
                        for (TreeNode leftTree : leftTrees) {
                            for (TreeNode rightTree : rightTrees) {
                                TreeNode root = new TreeNode(0);
                                root.left = leftTree;
                                root.right = rightTree;
                                ans.add(root);
                            }
                        }
                        leftTreeCnt += 2;
                    }
                    cache.put(n, ans);
                } else if (n % 2 == 0) {
                    return ans;
                }
            }

            return cache.get(n);
        }
    }
}
