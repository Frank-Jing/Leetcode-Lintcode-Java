package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC77_Combinations {
    class Solution {
        List<List<Integer>> results;
        int bound;
        public List<List<Integer>> combine(int n, int k) {
            results = new ArrayList<>();
            bound = n;
            backtrack(1, new LinkedList<Integer>(), k);

            return results;
        }

        public void backtrack(int i, LinkedList<Integer> comb, int k){
            if(comb.size() == k){
                results.add(new ArrayList<Integer>(comb));
                return;
            }

//            for(int cur = i; cur <= bound; cur++){
//                comb.add(cur);
//                backtrack(cur+1, comb, k);
//                comb.removeLast();
//            }

            // the most right position must allow at least remain elements to add
            for(int cur = i; cur <= bound - k + comb.size() + 1; cur++){
                comb.add(cur);
                backtrack(cur+1, comb, k);
                comb.removeLast();
            }
        }
    }
}
