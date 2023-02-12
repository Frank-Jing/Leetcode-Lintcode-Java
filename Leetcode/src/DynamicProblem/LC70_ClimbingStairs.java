package Leetcode.src.DynamicProblem;

public class LC70_ClimbingStairs {
    public int climbStairs(int n) {
        return process(n);
    }

    public int process(int stairs){
        if(stairs == 0){
            return 1;
        }else if(stairs < 0){
            return 0;
        }

        return process(stairs - 1) + process(stairs - 2);
    }

    public int climbStairs2(int n) {
        int[] dp = new int[n+2];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i<dp.length; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[dp.length - 1];
    }

    public int climbStairs3(int n) {
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 1; i<=n; i++){
            int cnt = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = cnt;
        }

        return dp[1];
    }
}
