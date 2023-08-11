package Leetcode.src.Backtrack;

import java.util.*;

public class LC47_Permutations_II {
    class Solution {
        List<List<Integer>> results;
        public List<List<Integer>> permuteUnique(int[] nums) {
            results = new ArrayList<>();

            Map<Integer, Integer> counter = new HashMap<>();
            for(int num : nums){
                counter.put(num, counter.getOrDefault(num, 0) + 1);
            }

            backtrack(new LinkedList<Integer>(), counter, nums);

            return results;
        }

        public void backtrack(LinkedList<Integer> perm, Map<Integer, Integer> counter, int[] nums){
            if(perm.size() == nums.length){
                results.add(new ArrayList<Integer>(perm));
                return;
            }

            for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
                Integer num = entry.getKey();
                Integer count = entry.getValue();
                if(count != 0){
                    perm.add(num);
                    counter.put(num, count - 1);
                    backtrack(perm, counter, nums);
                    counter.put(num, count);
                    perm.removeLast();
                }
            }

            return;
        }
    }
}
