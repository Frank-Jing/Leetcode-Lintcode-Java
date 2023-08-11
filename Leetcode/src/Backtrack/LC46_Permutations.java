package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LC46_Permutations {
    class Solution {
        List<List<Integer>> results;
        public List<List<Integer>> permute(int[] nums) {
            results = new ArrayList<>();
            int n = nums.length;
            boolean[] used = new boolean[n];

            backtrack(new LinkedList<Integer>(), used, nums);

            return results;
        }

        public void backtrack(LinkedList<Integer> perm, boolean[] used, int[] nums){
            if(perm.size() == nums.length){
                results.add(new ArrayList<Integer>(perm));
                return;
            }

            for(int i = 0; i < nums.length; i++){
                if(!used[i]){
                    used[i] = true;
                    perm.add(nums[i]);
                    backtrack(perm, used, nums);
                    perm.removeLast();
                    used[i] = false;
                }
            }

            return;
        }
    }

    class Solution2 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer> > result = new ArrayList<List<Integer> >();
            List<Integer> list = new ArrayList<Integer>();
            for (int num : nums) {
                list.add(num);
            }
            dfs(list, result, 0);
            return result;
        }

        public void dfs(List<Integer> list, List<List<Integer> > result, int start) {
            if (start == list.size()) {
                result.add(new ArrayList<Integer>(list));
                return;
            }

            for (int i = start; i<list.size(); i++) {
                Collections.swap(list, i, start);
                dfs(list, result, start + 1);
                Collections.swap(list, i, start);
            }
        }
    }
}
