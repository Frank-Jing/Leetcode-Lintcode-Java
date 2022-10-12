package Leetcode.src.BinaryTree;

import java.util.LinkedList;
import java.util.List;

public class LC108_SortedArrayToBST {
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            List<Integer> list = new LinkedList<>();
            for (Integer n : nums) {
                list.add(n);
            }
            return helper(list);
        }

        public TreeNode helper(List<Integer> nums) {
            if (nums.size() == 1) return new TreeNode(nums.get(0));
            if (nums.size() == 0) return null;

            int mid = nums.size() / 2;
            TreeNode head = new TreeNode(nums.get(mid));
            head.left = helper(nums.subList(0, mid));
            head.right = helper(nums.subList(mid + 1, nums.size()));

            return head;
        }
    }
}
