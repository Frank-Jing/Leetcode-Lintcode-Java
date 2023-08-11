package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC39_CombinationSum {
    class Solution {
        List<List<Integer>> results;
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            results = new ArrayList<>();
            backtrack(0, new LinkedList<Integer>(), 0, candidates, target);
            return results;
        }

        public void backtrack(int i, LinkedList<Integer> comb, int curSum, int[] candidates, int target){
            if(i == candidates.length){
                if(curSum == target){
                    results.add(new ArrayList<Integer>(comb));
                }
                return;
            }
            if(target >= curSum + candidates[i]){
                comb.add(candidates[i]);
                backtrack(i, comb, curSum + candidates[i], candidates, target);
                comb.removeLast();
            }
            if(target >= curSum){
                backtrack(i+1, comb, curSum, candidates, target);
            }
            return;
        }
    }
}
