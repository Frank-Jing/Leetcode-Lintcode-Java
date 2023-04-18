package Leetcode.src.MathBitOther;

import java.util.LinkedList;

public class LC121_BestTimetoBuySellStock {
    class Solution_MonotonicStack {
        public int maxProfit(int[] prices) {
            int max = Integer.MIN_VALUE;
            LinkedList<Integer> qmax = new LinkedList<>();
            for(int i = 0; i< prices.length; i++){
                while(!qmax.isEmpty() && prices[qmax.peekLast()] > prices[i]){
                    int temp = qmax.pollLast();
                    if(!qmax.isEmpty()){
                        max = Math.max(max, prices[temp] - prices[qmax.peekFirst()]);
                    }
                }
                qmax.add(i);
            }
            max = Math.max(prices[qmax.peekLast()] - prices[qmax.peekFirst()], max);

            return max;
        }
    }

    class Solution {
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
}
