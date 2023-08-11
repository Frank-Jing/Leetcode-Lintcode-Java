package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC254_FactorCombinations {
    class Solution {
        List<List<Integer>> results = new ArrayList<>();
        public List<List<Integer>> getFactors(int n) {
            if(n == 1) return results;
            backtrack(2, n, new LinkedList<Integer>());
            return results;
        }

        public void backtrack(int cur, int rest, LinkedList<Integer> list){
            if(rest == 1){
                return;
            }

            for(int i = cur; i < rest; i++){
                int division = rest/i;
                int remain = rest%i;
                if(remain == 0 && division >= i){
                    list.add(i);
                    list.add(division);
                    results.add(new ArrayList<Integer>(list));
                    list.removeLast();

                    backtrack(i, division, list);
                    list.removeLast();
                }
            }
        }
    }

    class Solution_best {
        List<List<Integer>> results = new ArrayList<>();
        public List<List<Integer>> getFactors(int n) {
            if(n == 1) return results;
            LinkedList<Integer> res = new LinkedList<>();
            res.add(n);
            backtrack(res);
            return results;
        }

        public void backtrack(LinkedList<Integer> list){

            if(list.size() > 1){
                results.add(new ArrayList<Integer>(list));
            }

            int lastFactor = list.removeLast();
            int start = list.size() == 0? 2 : list.getLast();
            // the restriction lastFactor/i is changing!
            for(int i = start; i <= lastFactor/i; i++){
                if(lastFactor%i == 0){
                    list.add(i);
                    list.add(lastFactor/i);
                    backtrack(list);
                    list.removeLast();
                    list.removeLast();
                }
            }
            // don't forget to add
            list.add(lastFactor);
        }
    }
}
