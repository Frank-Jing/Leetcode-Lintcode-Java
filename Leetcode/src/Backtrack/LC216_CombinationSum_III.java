package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC216_CombinationSum_III {
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        public List<List<Integer>> combinationSum3(int k, int n) {
            backtrack(k, n, 1);

            return res;
        }

        private void backtrack(int k, int n, int start) {
            if (k == 0) {
                if (n == 0) {
                    res.add(new ArrayList<>(list));
                    return;
                }
            }

            for (int i = start; i <= 9; i++) {
                if (i > n) {
                    return;
                }
                list.add(i);
                backtrack(k - 1, n - i, i + 1);
                list.removeLast();
            }
        }
    }

    class Solution_slow {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();
        public List<List<Integer>> combinationSum3(int k, int n) {
            backtrack(0, 1, k, n);

            return results;
        }

        public void backtrack(int curSum, int curVal, int k, int n){
            if(comb.size() == k){
                if(curSum == n){
                    results.add(new ArrayList<Integer>(comb));
                }
                return;
            }

            for(int nextVal = curVal; nextVal <= Math.min(n - k + comb.size(), 9); nextVal++){
                if(curSum + nextVal <= n){
                    comb.add(nextVal);
                    backtrack(curSum + nextVal, nextVal + 1, k, n);
                    comb.removeLast();
                }
            }
        }

    }
}
