package Leetcode.src.DynamicProblem;

public class LC121_BestTimeToSellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++){
            int curPrice = prices[i];
            if(curPrice < minPrice){
                minPrice = curPrice;
            }
            if(maxProfit < curPrice - minPrice){
                maxProfit = curPrice - minPrice;
            }
        }
        return maxProfit;
    }
}
