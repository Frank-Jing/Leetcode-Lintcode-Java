package Leetcode.src.DynamicProblem;

import java.util.Arrays;

public class LC322_CoinChange {
    class Solution_recursion {
        public int coinChange(int[] coins, int amount) {
            int ans = process(coins, amount, 0);
            if(ans == Integer.MAX_VALUE){
                return -1;
            }
            return ans;
        }

        public int process(int[] coins, int amountLeft, int pos){
            if(pos == coins.length){
                return amountLeft == 0? 0 : Integer.MAX_VALUE;
            }

            int cnt = Integer.MAX_VALUE;
            for(int i = pos; i < coins.length; i++){
                for(int j = 0; j*coins[i] <= amountLeft; j++){
                    int follow = process(coins, amountLeft - j*coins[i], i+1);
                    if(follow != Integer.MAX_VALUE){
                        cnt = Math.min(cnt, follow + j);
                    }
                }
            }

            return cnt;
        }
    }

    class Solution_recursion_optimized {
        public int coinChange(int[] coins, int amount) {
            int ans = process(coins, amount);
            if(ans == Integer.MAX_VALUE){
                return -1;
            }
            return ans;
        }

        public int process(int[] coins, int amountLeft){
            if(amountLeft == 0) return 0;

            int cnt = Integer.MAX_VALUE;
            for(int coin : coins){
                if(amountLeft - coin >= 0){
                    int way = process(coins, amountLeft - coin);
                    if(way != Integer.MAX_VALUE){
                        cnt = Math.min(cnt, 1 + process(coins, amountLeft - coin));
                    }
                }
            }

            return cnt;
        }
    }

    class Solution_dp {
        public int coinChange(int[] coins, int amount) {
            if(amount == 0) return 0;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

            for(int i=1; i <= amount; i++){
                for(int coin : coins){
                    if(i - coin >= 0){
                        dp[i] = Math.min(dp[i - coin], dp[i]);
                    }
                }
                if(dp[i] != Integer.MAX_VALUE){
                    dp[i] += 1;
                }
            }

            return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
        }
    }

}
