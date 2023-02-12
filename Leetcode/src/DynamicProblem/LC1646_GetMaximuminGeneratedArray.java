package Leetcode.src.DynamicProblem;

public class LC1646_GetMaximuminGeneratedArray {
    public int getMaximumGenerated(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        int max = 1;
        for(int i = 1; i*2 + 1<=n; i++){
            dp[i*2] = dp[i];
            dp[i*2 + 1] = dp[i] + dp[i+1];
            max = Math.max(max, Math.max(dp[i*2+1], dp[i*2]));
        }

        return max;
    }
}
