package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC78_Subsets {
    class Solution {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> curSet = new LinkedList<>(); // would be overridden during recursion
        public List<List<Integer>> subsets(int[] nums) {
            backtrack(0, curSet, nums);
            return results;
        }

        public void backtrack(int i, LinkedList<Integer> curSet, int[] nums){
            results.add(new ArrayList<Integer>(curSet));
            for(int curInd = i; curInd < nums.length; curInd++){
                curSet.add(nums[curInd]);
                backtrack(curInd+1, curSet, nums);
                curSet.removeLast();
            }

            return;
        }
    }
}
