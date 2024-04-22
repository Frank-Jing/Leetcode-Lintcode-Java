package Leetcode.src.DynamicProblem;

public class LC2361_MinCostTrainLines {
    class Solution_dp {
        public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
            int n = regular.length + 1;
            long[][] dp = new long[n][2];
            long[] res = new long[n-1];
            // index 0 as regular, index 1 as express
            dp[0][0] = 0;
            dp[0][1] = expressCost;
    
            for(int i = 1; i < n; i++){
                dp[i][0] = regular[i-1] + Math.min(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = Math.min(dp[i-1][1], dp[i-1][0] + expressCost) + express[i-1];
                res[i-1] = Math.min(dp[i][0], dp[i][1]);
            }
    
            return res;
        }
    }

    class Solution_dp2 {
        public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
            int n = regular.length;
            long[] dp = new long[2];
            long[] res = new long[n];
            // index 0 as regular, index 1 as express
            dp[0] = 0;
            dp[1] = expressCost;
    
            for(int i = 0; i < n; i++){
                long cost_0 = dp[0];
                long cost_1 = dp[1];
                dp[0] = regular[i] + Math.min(cost_0, cost_1);
                dp[1] = Math.min(cost_1, cost_0 + expressCost) + express[i];
                res[i] = Math.min(dp[0], dp[1]);
            }
    
            return res;
        }
    }
}
