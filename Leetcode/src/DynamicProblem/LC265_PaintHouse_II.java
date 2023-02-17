package Leetcode.src.DynamicProblem;

public class LC265_PaintHouse_II {
    class Solution {
        public int minCostII(int[][] costs) {
            int firstMin = Integer.MAX_VALUE, secMin = Integer.MAX_VALUE;
            int preFirstMin = Integer.MAX_VALUE, preSecMin = Integer.MAX_VALUE;
            int preColor = 0;
            for (int i = 0; i < costs[0].length; i++) {
                if (costs[0][i] < secMin) {
                    secMin = costs[0][i];
                    if (costs[0][i] < firstMin) {
                        preColor = i;
                        secMin = firstMin;
                        firstMin = costs[0][i];
                    }
                }
            }
            if (costs.length == 1) return firstMin;

            preFirstMin = firstMin;
            preSecMin = secMin;

            for (int row = 1; row < costs.length; row++) {
                int[] cost = costs[row];
                int minColor = 0;
                secMin = Integer.MAX_VALUE;
                firstMin = Integer.MAX_VALUE;
                for (int col = 0; col < cost.length; col++) {
                    if (col != preColor) {
                        cost[col] += preFirstMin;
                    } else {
                        cost[col] += preSecMin;
                    }

                    if (cost[col] < secMin) {
                        secMin = cost[col];
                        if (cost[col] < firstMin) {
                            minColor = col;
                            secMin = firstMin;
                            firstMin = cost[col];
                        }
                    }
                }
                preColor = minColor;
                preFirstMin = firstMin;
                preSecMin = secMin;
            }

            return preFirstMin;
        }
    }
}
