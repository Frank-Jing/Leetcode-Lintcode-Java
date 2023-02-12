package Leetcode.src.DynamicProblem;

public class LC746_MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        return process(cost, -1);
    }

    public int process(int[] cost, int pos){
        if(pos >= cost.length){
            return 0;
        }
        //jump 1 step
        int cost1 = 0;
        if(pos + 1 <= cost.length - 1){
            cost1 = cost[pos+1] + process(cost, pos+1);
        }
        //jump 2 steps
        int cost2 = 0;
        if(pos + 2 <= cost.length - 1)
            cost2 = cost[pos+2] + process(cost, pos+2);

        return Math.min(cost1, cost2);
    }

    public int minCostClimbingStairs_2(int[] cost) {
        int N = cost.length;
        int[] dp = new int[N+1];
        dp[N] = 0;
        dp[N-1] = cost[N-1];
        for(int i = N-2; i>= 0; i--){
            dp[i] = cost[i] + Math.min(dp[i+1], dp[i+2]);
        }

        return Math.min(dp[0], dp[1]);
    }
}
