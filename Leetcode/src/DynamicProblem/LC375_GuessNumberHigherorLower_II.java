package Leetcode.src.DynamicProblem;

public class LC375_GuessNumberHigherorLower_II {
    class Solution_recursion {
        public int getMoneyAmount(int n) {
            return process(1, n);
        }

        public int process(int l, int r){
            if(l >= r) return 0;
            if(l + 1 == r) return l;
            if(l + 2 == r) return l+1;

            int res = Integer.MAX_VALUE;
            for(int i = l; i<= r; i++){
                int cost = i + Math.max(process(l, i-1), process(i+1, r));
                res = Math.min(cost, res);
            }

            return res;
        }
    }

    class Solution_dp {
        public int getMoneyAmount(int n) {
            if(n == 1) return 0;
            if(n == 2) return 1;
            if(n == 3) return 2;
            int[][] dp = new int[n][n];

            for(int i = 0; i<n-1; i++){
                dp[i][i+1] = i+1;

            }

            for(int i=0; i < n -2; i++){
                dp[i][i+2] = i+2;
            }

            for(int i = n-4; i >= 0; i--){
                for(int j = i+3; j< n; j++){
                    // dp[i][j] = i+1 + dp[i+1][j];
                    // dp[i][j] = Math.min(dp[i][j], j + dp[i][j-1]);
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i+1; k <= j; k++){
                        int cost = dp[i][k-1];
                        if(k+1 < j) cost = Math.max(dp[k+1][j], cost);
                        dp[i][j] = Math.min(dp[i][j], cost + k +1);
                    }
                }
            }

            return dp[0][n-1];
        }
    }
}
