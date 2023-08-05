package Leetcode.src.DynamicProblem;

public class LC2466_CountWaysToBuildGoodStrings {
    class Solution {
        public int countGoodStrings(int low, int high, int zero, int one) {
            int[] dp = new int[high + 1];
            int mod = 1_000_000_007;
            dp[0] = 1;
            for(int i = 1; i<= high; i++){
                if(i >= zero){
                    dp[i] += dp[i - zero];
                }
                if(i >= one){
                    dp[i] += dp[i - one];
                }
                dp[i] %= mod;
            }

            int ans = 0;
            for(int ind = low; ind <= high; ind++){
                ans += dp[ind];
                ans %= mod;
            }

            return ans%mod;
        }
    }
}
