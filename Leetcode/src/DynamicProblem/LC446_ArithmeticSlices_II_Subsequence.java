package Leetcode.src.DynamicProblem;

import java.util.HashMap;
import java.util.Map;

public class LC446_ArithmeticSlices_II_Subsequence {
    class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;
            Map<Integer, Integer>[] consec2Subseq = new Map[n];
            int ans = 0;

            for(int i = 0; i < n; i++){
                consec2Subseq[i] = new HashMap<>();
                for(int j = 0; j < i; j++){
                    long diff = (long)nums[i] - (long)nums[j];
                    if(diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE){
                        continue;
                    }
                    int delta = (int)diff;

                    int preSeq = consec2Subseq[j].getOrDefault(delta, 0);
                    int curSameDiffSeq = consec2Subseq[i].getOrDefault(delta, 0);
                    consec2Subseq[i].put(delta, preSeq + curSameDiffSeq + 1);
                    ans += preSeq;
                }
            }

            return ans;
        }
    }
}
