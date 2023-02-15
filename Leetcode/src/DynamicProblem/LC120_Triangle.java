package Leetcode.src.DynamicProblem;

import java.util.List;

public class LC120_Triangle {
    class Solution_recursion {
        public int minimumTotal(List<List<Integer>> triangle) {
            return process(triangle, 0, 0);
        }

        //start from i, j in the triangle, return the minimum sum
        public int process(List<List<Integer>> triangle, int i, int j){
            if(i == triangle.size() - 1){
                return triangle.get(i).get(j);
            }

            int sum = triangle.get(i).get(j);
            sum += Math.min(process(triangle, i+1, j), process(triangle, i+1, j+1));

            return sum;
        }
    }

    class Solution_dp {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[][] dp = new int[n+1][n+1];

            for (int level=n-1; level>=0; level--)
                for (int i=0; i<=level; i++)
                    dp[level][i] = triangle.get(level).get(i) + Math.min(dp[level+1][i], dp[level+1][i+1]);

            return dp[0][0];
        }
    }

    class Solution_dp_noExtraSpace {
        public int minimumTotal(List<List<Integer>> triangle) {
            int M = triangle.size();

            for(int i = M-2; i>= 0; i--){
                for(int j = 0; j < triangle.get(i).size(); j++){
                    int nextLayerSum = Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
                    triangle.get(i).set(j, triangle.get(i).get(j) + nextLayerSum);
                }
            }

            return triangle.get(0).get(0);
        }
    }
}
