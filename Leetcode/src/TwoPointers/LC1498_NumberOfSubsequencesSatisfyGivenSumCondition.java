package Leetcode.src.TwoPointers;

import java.util.Arrays;

public class LC1498_NumberOfSubsequencesSatisfyGivenSumCondition {
    class Solution {
        public int numSubseq(int[] nums, int target) {
            int cnt = 0;
            int mod = 1_000_000_007;
            int n = nums.length;
            Arrays.sort(nums);

            int[] power = new int[n];
            power[0] = 1;
            for (int i = 1; i < n; ++i) {
                power[i] = (power[i - 1] * 2) % mod;
            }

            int left = 0, right = n - 1;
            while(left <= right){
                if(nums[left] + nums[right] <= target){
                    cnt += power[right - left];
                    cnt %= mod;
                    left++;
                }else{
                    right--;
                }
            }

            return cnt;
        }
    }
}
