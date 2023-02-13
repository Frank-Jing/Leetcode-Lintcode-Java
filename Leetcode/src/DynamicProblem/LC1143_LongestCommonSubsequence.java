package Leetcode.src.DynamicProblem;

public class LC1143_LongestCommonSubsequence {
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            char[] str1 = text1.toCharArray();
            char[] str2 = text2.toCharArray();
            int N = str1.length;
            int M = str2.length;

            return process(str1, str2, N-1, M-1);
        }

        public int process(char[] s1, char[] s2, int i, int j){
            if(i == 0 & j == 0){
                return s1[i] == s2[j]? 1 : 0;
            }else if(i == 0){
                if(s1[i] == s2[j]){
                    return 1;
                }else{
                    return process(s1, s2, 0, j-1);
                }
            }else if(j == 0){
                if(s1[i] == s2[j]){
                    return 1;
                }else{
                    return process(s1, s2, i-1, 0);
                }
            }else{
                int cnt1 = process(s1, s2, i-1, j);
                int cnt2 = process(s1, s2, i, j-1);
                int cnt3 = s1[i] == s2[j]? 1 + process(s1, s2, i-1, j-1) : 0;

                return Math.max(Math.max(cnt1, cnt2), cnt3);
            }
        }
    }

    class Solution_dp {
        public int longestCommonSubsequence(String text1, String text2) {
            char[] s1 = text1.toCharArray();
            char[] s2 = text2.toCharArray();
            int N = s1.length;
            int M = s2.length;
            int[][] dp = new int[N][M];
            dp[0][0] = s1[0] == s2[0] ? 1:0;
            for(int i = 1; i < N; i++){
                dp[i][0] = s1[i] == s2[0]? 1: dp[i-1][0];
            }

            for(int j = 1; j < M; j++){
                dp[0][j] = s1[0] == s2[j]? 1 : dp[0][j-1];
            }

            for(int i = 1; i< N; i++){
                for(int j = 1; j < M; j++){
                    int cnt1 = dp[i-1][j];
                    int cnt2 = dp[i][j-1];
                    int cnt3 = s1[i] == s2[j]? 1 + dp[i-1][j-1] : 0;
                    dp[i][j] = Math.max(Math.max(cnt1, cnt2), cnt3);
                }
            }

            return dp[N-1][M-1];
        }
    }
}
