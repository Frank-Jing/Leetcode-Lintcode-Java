package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC90_Subsets_II {
    class Solution {
        List<List<Integer>> results = new ArrayList<>();
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            backtrack(0, new LinkedList<Integer>(), nums);

            return results;
        }

        public void backtrack(int i, LinkedList<Integer> set, int[] nums){
            results.add(new ArrayList<Integer>(set));

            for(int curInd = i; curInd< nums.length; curInd++){
                if(curInd > i && nums[curInd] == nums[curInd - 1]){
                    continue;
                }

                set.add(nums[curInd]);
                backtrack(curInd+1, set, nums);
                set.removeLast();
            }
        }
    }
}
