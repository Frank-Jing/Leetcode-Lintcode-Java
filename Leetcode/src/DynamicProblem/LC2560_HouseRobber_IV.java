package Leetcode.src.DynamicProblem;

public class LC2560_HouseRobber_IV {
    class Solution_DP {
        int[] dp = new int[2];
        public int minCapability(int[] nums, int k) {
            int l = 0, r = 1000000000;
            while(l < r){
                int mid = l + (r-l)/2;
                if(atLeastK(mid, nums, k)){
                    r = mid;
                }else{
                    l = mid + 1;
                }
            }
            return l;
        }

        public boolean atLeastK(int cap, int[] nums, int k){
            int n = nums.length;
            dp[0] = 0; // at the house i, non_rob, return current number of house robbed
            if(nums[0] <= cap){
                dp[1] = 1;
            }else{
                dp[1] = -1; // invalid rob
            }

            for(int i = 1; i < n; i++){
                int nonRob = Math.max(dp[0], dp[1]);
                if(nums[i] <= cap){
                    dp[1] = dp[0] + 1; // no adjacent house
                }else{
                    dp[1] = -1;
                }
                dp[0] = nonRob;
            }

            return Math.max(dp[0], dp[1]) >= k;
        }
    }


    class Solution {
        public int minCapability(int[] nums, int k) {
            int l = 0, r = 1000000000;
            while(l < r){
                int mid = l + (r-l)/2;

                int visited = 0;
                for(int i = 0; i < nums.length; i++){
                    if(nums[i] <= mid){
                        visited++;
                        i++;
                    }
                }

                if(visited >= k){
                    r = mid;
                }else{
                    l = mid + 1;
                }
            }
            return l;
        }
    }
}
