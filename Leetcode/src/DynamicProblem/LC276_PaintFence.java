package Leetcode.src.DynamicProblem;

public class LC276_PaintFence {
    class Solution_recursion {
        public int numWays(int n, int k) {
            return process(k, n);
        }

        public int process(int k, int i){
            if(i == 0) return 0;
            if(i == 1) return k;
            if(i == 2) return k*k;

            int ways = process(k, i-1) * (k-1); // different colot for second
            ways += process(k, i-2) * (k-1); // same color for second but k-1 color for the third

            return ways;
        }
    }

    class Solution_dp {
        public int numWays(int n, int k) {
            if(n == 1) return k;
            if(n == 2) return k*k;

            int[] dp = new int[n];
            dp[0] = k;
            dp[1] = k*k;
            for(int i = 2; i<n; i++){
                dp[i] = (k-1) * (dp[i-2] + dp[i-1]);
            }

            return dp[n-1];
        }
    }
}
