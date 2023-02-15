package Leetcode.src.DynamicProblem;

public class LC256_PaintHouse {
    class Solution_dp {
        public int minCost(int[][] costs) {
            int M = costs.length;
            for(int row = 1; row < M ; row++){
                costs[row][0] = Math.min(costs[row-1][1], costs[row-1][2]) + costs[row][0];
                costs[row][1] = Math.min(costs[row-1][0], costs[row-1][2]) + costs[row][1];
                costs[row][2] = Math.min(costs[row-1][0], costs[row-1][1]) + costs[row][2];
            }
            return Math.min(costs[M-1][0], Math.min(costs[M-1][1], costs[M-1][2]));
        }
    }
}
