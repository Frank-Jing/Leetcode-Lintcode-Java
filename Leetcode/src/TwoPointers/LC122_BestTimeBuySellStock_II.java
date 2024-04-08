package Leetcode.src.TwoPointers;

public class LC122_BestTimeBuySellStock_II {
    class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int minInd = 0, maxInd = 0;
            for(int i = 1; i < prices.length; i++){
                if(prices[i] < prices[maxInd]){
                    maxProfit += prices[maxInd] - prices[minInd];
                    minInd = i;
                    maxInd = i;
                }else if(prices[i] >= prices[maxInd]){
                    maxInd = i;
                }
            }
            maxProfit += prices[maxInd] - prices[minInd];

            return maxProfit;
        }
    }

    class Solution_2 {
        public int maxProfit(int[] prices) {
            int maxprofit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1])
                    maxprofit += prices[i] - prices[i - 1];
            }
            return maxprofit;
        }
    }
}
