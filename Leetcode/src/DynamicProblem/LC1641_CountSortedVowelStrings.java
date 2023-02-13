package Leetcode.src.DynamicProblem;

public class LC1641_CountSortedVowelStrings {
    class Solution_recursion {
        public int countVowelStrings(int n) {
            return process(0, n);
        }

        // start with ith vowel in "aeiou"
        // return combination count when there are n spaces to fill
        public int process(int i, int n){
            // if now we have only "u" to fill rest of spaces
            // only 1 choice to fill as "uuu..."
            if(i == 4) return 1;
            // if(n == 0) return 0;
            // when there is only 1 space to fill
            // we currently stand at ith vowel, we have 5-i choices
            // for example, we start from "o", then there are 2 as "o" and "u"
            if(n == 1) return 5-i;
            int ways = 0;
            // we currently stand at ith vowel
            // the total is the sum to fill with n - 1 spaces with different vowels remained
            for(int ind = i; ind <= 4; ind++){
                ways += process(ind, n - 1);
            }
            return ways;
        }
    }

    class Solution_dp {
        public int countVowelStrings(int n) {
            int[][] dp = new int[5][n];
            for(int j = 0; j < n; j++){
                dp[4][j] = 1;
            }
            for(int i = 0; i < 4; i++){
                dp[i][0] = 5 - i;
            }

            for(int j = 1; j < n; j++){
                for(int i = 3; i >= 0; i--){
                    int ways = 0;
                    for(int ind = i; ind <= 4; ind++){
                        ways += dp[ind][j-1];
                    }
                    dp[i][j]= ways;
                }
            }

            return dp[0][n-1];
        }
    }
    class Solution_dp_optimized {
        public int countVowelStrings(int n) {
            int[][] dp = new int[5][n];
            for(int j = 0; j < n; j++){
                dp[4][j] = 1;
            }
            for(int i = 0; i < 4; i++){
                dp[i][0] = 5 - i;
            }

            for(int j = 1; j < n; j++){
                for(int i = 3; i >= 0; i--){
                    int ways = dp[i+1][j];
                    ways += dp[i][j-1];
                    dp[i][j]= ways;
                }
            }

            return dp[0][n-1];
        }
    }
}
