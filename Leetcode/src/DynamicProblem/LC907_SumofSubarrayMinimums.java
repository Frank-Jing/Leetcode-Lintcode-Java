package Leetcode.src.DynamicProblem;

public class LC907_SumofSubarrayMinimums {
    //exceed the memory limit
    class Solution {
        public int sumSubarrayMins(int[] arr) {
            int N = arr.length;
            int[][] dp = new int[N][N];
            int ans = 0;
            int mod = 7 + (int)Math.pow(10, 9);

            for(int i = N-1; i>=0; i--){
                for(int j = i; j < N; j++){
                    dp[i][j] = arr[j];
                    if(i!=j){
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1]);
                    }
                    ans = ans%mod + dp[i][j];
                }
            }

            return ans%mod;
        }
    }
}
