package Leetcode.src.DynamicProblem;

import java.util.Arrays;

public class LC935_KnightDialer {
    class Solution_recursion {
        public int knightDialer(int n) {
            if (n == 1) return 10;
            int ways = 0;
            for (int i = 0; i < 10; i++) {
                ways += process(n, i);
            }
            return ways;
        }

        public int process(int j, int i) {
            if (j == 1) return 1;

            int cnt = 0;
            if (i == 0) {
                cnt = process(j - 1, 6) + process(j - 1, 4);
            } else if (i == 1) {
                cnt = process(j - 1, 6) + process(j - 1, 8);
            } else if (i == 2) {
                cnt = process(j - 1, 7) + process(j - 1, 9);
            } else if (i == 3) {
                cnt = process(j - 1, 4) + process(j - 1, 8);
            } else if (i == 4) {
                cnt = process(j - 1, 3) + process(j - 1, 9) + process(j - 1, 0);
            } else if (i == 5) {
                cnt = 0;
            } else if (i == 6) {
                cnt = process(j - 1, 1) + process(j - 1, 7) + process(j - 1, 0);
            } else if (i == 7) {
                cnt = process(j - 1, 2) + process(j - 1, 6);
            } else if (i == 8) {
                cnt = process(j - 1, 1) + process(j - 1, 3);
            } else if (i == 9) {
                cnt = process(j - 1, 4) + process(j - 1, 2);
            }
            return cnt;
        }
    }

    class Solution_dp {
        public int knightDialer(int n) {
            if (n == 1) return 10;
            long[][] dp = new long[n][10];
            Arrays.fill(dp[0], 1);

            for (int j = 1; j < n; j++) {
                for (int i = 0; i <= 9; i++) {
                    long cnt = 0;
                    if (i == 0) {
                        cnt = dp[j - 1][6] + dp[j - 1][4];
                    } else if (i == 1) {
                        cnt = dp[j - 1][6] + dp[j - 1][8];
                    } else if (i == 2) {
                        cnt = dp[j - 1][7] + dp[j - 1][9];
                    } else if (i == 3) {
                        cnt = dp[j - 1][4] + dp[j - 1][8];
                    } else if (i == 4) {
                        cnt = dp[j - 1][3] + dp[j - 1][9] + dp[j - 1][0];
                    } else if (i == 5) {
                        cnt = 0;
                    } else if (i == 6) {
                        cnt = dp[j - 1][1] + dp[j - 1][7] + dp[j - 1][0];
                    } else if (i == 7) {
                        cnt = dp[j - 1][2] + dp[j - 1][6];
                    } else if (i == 8) {
                        cnt = dp[j - 1][1] + dp[j - 1][3];
                    } else if (i == 9) {
                        cnt = dp[j - 1][4] + dp[j - 1][2];
                    }
                    dp[j][i] = cnt % (7 + (long) Math.pow(10, 9));
                }
            }

            long ways = 0;
            for (int i = 0; i < 10; i++) {
                ways += dp[n - 1][i];
            }

            ways = ways % (7 + (long) Math.pow(10, 9));
            int ans = (int) ways;
            return ans;
        }
    }
}
