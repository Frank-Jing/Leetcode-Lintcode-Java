package Leetcode.src.BinarySearch;

public class LC2560_HouseRobber_IV {
    class Solution_Backtrack_TLE {
        int minCap = Integer.MAX_VALUE;
        public int minCapability(int[] nums, int k) {
            for(int i = 0; i<nums.length; i++){
                backtrack(nums, i, nums[i], k-1);
            }
            return minCap;
        }

        public void backtrack(int[] nums, int start, int cap, int k){
            if(k <= 0){
                minCap = Math.min(minCap, cap);
                return;
            }else if(start >= nums.length){
                return;
            }

            for(int next = start + 2; next < nums.length; next++){
                int curCap = Math.max(cap, nums[next]);
                backtrack(nums, next, curCap, k-1);
            }

            return;
        }


    }
}
