package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC40_CombinationSum_II {
    class Solution {
        List<List<Integer>> results;
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            results = new ArrayList<>();
            Arrays.sort(candidates);
            backtrack(0, new LinkedList<Integer>(), 0, candidates, target);
            return results;
        }

        public void backtrack(int i, LinkedList<Integer> comb, int curSum, int[] candidates, int target){
            if(curSum > target) return;

            if(i == candidates.length){
                if(curSum == target){
                    results.add(new ArrayList<Integer>(comb));
                }
                return;
            }

            if(comb.size() == 0 || comb.getLast() <= candidates[i]){
                comb.add(candidates[i]);
                backtrack(i+1, comb, curSum + candidates[i], candidates, target);
                comb.removeLast();
            }
            while(i<candidates.length-1 && candidates[i] == candidates[i+1]){
                i++;
            }
            backtrack(i+1, comb, curSum, candidates, target);
            return;
        }
    }

    class Solution2 {
        List<List<Integer>> results;
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            results = new ArrayList<>();
            Arrays.sort(candidates);
            backtrack(0, new LinkedList<Integer>(), 0, candidates, target);
            return results;
        }

        public void backtrack(int i, LinkedList<Integer> comb, int curSum, int[] candidates, int target){
            if(curSum == target){
                results.add(new ArrayList<Integer>(comb));
                return;
            }

            for(int addInd = i; addInd < candidates.length; addInd++){
                if(addInd > i && candidates[addInd] == candidates[addInd - 1]){
                    continue;
                }

                if(curSum + candidates[addInd] > target){
                    break; // candidates sorted, the following number would not be valid
                }
                comb.add(candidates[addInd]);
                backtrack(addInd + 1, comb, curSum + candidates[addInd], candidates, target);
                comb.removeLast();
            }
            return;
        }
    }
}
