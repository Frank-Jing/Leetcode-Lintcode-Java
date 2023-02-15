package Leetcode.src.DynamicProblem;

public class LC279_PerfectSquares {
    class Solution_recursion{
        public int numSquares(int n) {
            return process(n);
        }

        public int process(int n){
            if(n == 0){
                return 0;
            }
            if(n == 1){
                return 1;
            }
            int ways = Integer.MAX_VALUE;
            for(int i = 1; i*i <= n; i++){
                ways = Math.min(ways, process(n - i*i) + 1);
            }
            return ways;
        }
    }

    class Solution_dp {
        public int numSquares(int n) {
            if(n == 0) return 0;
            if(n == 1) return 1;
            int[] dp = new int[n+1];

            dp[0] = 0;
            dp[1] = 1;

            for(int i = 2; i <= n; i++){
                int ways = Integer.MAX_VALUE;
                for(int j = 1; j*j <= i; j++){
                    ways = Math.min(ways, dp[i - j*j] + 1);
                }
                dp[i] = ways;
            }
            return dp[n];
        }
    }
}
