package Leetcode.src.DynamicProblem;

public class LC123_BestTimeBuySellStock_III {
    class Solution {
        public int maxProfit(int[] prices) {
            int hold1=Integer.MIN_VALUE, sold1=0, hold2=Integer.MIN_VALUE, sold2=0;

            for(int price : prices){
                int hold1_temp = hold1;
                int sold1_temp = sold1;
                int hold2_temp = hold2;
                int sold2_temp = sold2;

                hold1 = Math.max(hold1_temp, 0 - price);
                sold1 = Math.max(sold1_temp, hold1_temp + price);
                hold2 = Math.max(hold2_temp, sold1_temp - price);
                sold2 = Math.max(sold2_temp, hold2_temp + price);
            }

            return Math.max(sold1, sold2);
        }
    }
}
