package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC113_PathSum_II {
    class Solution {
        List<List<Integer>> ans = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if(root == null) return ans;
            List<Integer> path = new LinkedList<>();
            process(root, targetSum, path);
            return ans;
        }

        public void process(TreeNode root, int targetSum, List<Integer> prePath){
            if(root == null) return;

            prePath.add(root.val);
            if(root.left == null && root.right == null && root.val == targetSum){
                ans.add(new ArrayList(prePath));
                //ans.add(prePath)
                //this would result in [[],[]]
                //List is not like integer (refer to LC112-PathSum), it's a pointer to a place in RAM
                //directly add would lead to dynamic content
                //at last, prePath would be [] because each recursion level would reduce tail element
                //finally return to root/head, which would be []
            }else{
                process(root.left, targetSum - root.val, prePath);
                process(root.right, targetSum - root.val, prePath);
            }

            prePath.remove(prePath.size() - 1);
        }
    }
}
