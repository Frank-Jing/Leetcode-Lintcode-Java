package Leetcode.src.DynamicProblem;

public class LC2140_SolvingQuestionsWithBrainpower {
    class Solution {
        public long mostPoints(int[][] questions) {
            int N = questions.length;
            long[] dp = new long[N + 1];
            dp[N] = 0;
            for(int i = N-1; i>= 0; i--){
                long ans1 = i+1 == N? 0 : dp[i+1];
                long ans2 = i + questions[i][1] + 1 >= N? 0 : dp[i + questions[i][1] + 1];
                ans2 += questions[i][0];
                dp[i] = Math.max(ans1, ans2);
            }

            return dp[0];
        }

        // public long process(int[][] questions, int cur){
        //     if(cur >= questions.length){
        //         return 0;
        //     }

        //     long ans1 = process(questions, cur+1);
        //     long ans2 = questions[cur][0] + process(questions, cur + questions[cur][1] + 1);

        //     return Math.max(ans1, ans2);
        // }
    }
}
