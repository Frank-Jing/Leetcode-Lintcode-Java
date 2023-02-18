package Leetcode.src.DynamicProblem;

public class LC91_DecodeWays {
    class Solution_recursion {
        public int numDecodings(String s) {
            return process(s, 0);
        }

        public int process(String s, int i) {
            if (i == s.length()) return 1; //we find a way
            if (s.charAt(i) == '0') return 0; //can't start with 0

            int ways = process(s, i + 1);
            if (i + 1 < s.length() && (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') < 27) {
                ways += process(s, i + 2);
            }
            return ways;
        }
    }

    class Solution_dp {
        public int numDecodings(String s) {
            int N = s.length();
            int[] dp = new int[N + 1];
            dp[N] = 1;

            for (int i = N - 1; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    dp[i] = 0;
                } else {
                    dp[i] = dp[i + 1];
                    if (i + 1 < N && (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') < 27) {
                        dp[i] += dp[i + 2];
                    }
                }
            }

            return dp[0];
        }
    }
}
