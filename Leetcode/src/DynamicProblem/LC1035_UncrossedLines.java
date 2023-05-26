package Leetcode.src.DynamicProblem;

// same as LC1143 Longest Common Subsequence
public class LC1035_UncrossedLines {
    class Solution {
        public int maxUncrossedLines(int[] nums1, int[] nums2) {
            int N = nums1.length;
            int M = nums2.length;
            int[][] dp = new int[N][M];
            dp[0][0] = nums1[0] == nums2[0] ? 1:0;
            for(int i = 1; i < N; i++){
                dp[i][0] = nums1[i] == nums2[0]? 1: dp[i-1][0];
            }

            for(int j = 1; j < M; j++){
                dp[0][j] = nums1[0] == nums2[j]? 1 : dp[0][j-1];
            }

            for(int i = 1; i< N; i++){
                for(int j = 1; j < M; j++){
                    int cnt1 = dp[i-1][j];
                    int cnt2 = dp[i][j-1];
                    int cnt3 = nums1[i] == nums2[j]? 1 + dp[i-1][j-1] : 0;
                    dp[i][j] = Math.max(Math.max(cnt1, cnt2), cnt3);
                }
            }

            return dp[N-1][M-1];
        }
    }
}
