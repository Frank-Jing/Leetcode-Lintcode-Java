package Leetcode.src.DynamicProblem;

public class LC72_EditDistance {
    class Solution_recursion {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length();
            int len2 = word2.length();

            return process(word1, word2, len1 - 1, len2 - 1);
        }

        public int process(String word1, String word2, int i, int j) {
            if (i < 0 || j < 0) {
                return i < 0 ? j + 1 : i + 1;
            }

            if (word1.charAt(i) == word2.charAt(j)) {
                return process(word1, word2, i - 1, j - 1);
            }

            int ans = Math.min(
                    process(word1, word2, i - 1, j) + 1, //delete word1 char i, then compare word1[0, i-1] with word2[0, j]
                    Math.min(process(word1, word2, i, j - 1) + 1, //insert a char as j, then compare word1[0,i] with word2[0, j-1]
                            process(word1, word2, i - 1, j - 1) + 1 //replace, then compare word1[0,i-1] wth word2[0, j-1]
                    ));
            return ans;
        }
    }

    class Solution_dp {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length();
            int len2 = word2.length();

            //first row and first column to handle i<0 and j<0 in recursion
            int[][] dp = new int[len1 + 1][len2 + 1];

            // fill value when word2 index <0 in recursion
            for (int i = 0; i <= len1; i++) {
                dp[i][0] = i;
            }
            // fill value when word1 index <0 in recursion
            for (int j = 0; j <= len2; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                    }
                }
            }

            return dp[len1][len2];
        }
    }
}
