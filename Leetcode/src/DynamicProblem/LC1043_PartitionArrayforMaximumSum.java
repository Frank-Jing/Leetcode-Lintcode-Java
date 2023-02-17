package Leetcode.src.DynamicProblem;

import java.util.HashMap;
import java.util.Map;

public class LC1043_PartitionArrayforMaximumSum {
    class Solution_recursion_2D {
        public int maxSumAfterPartitioning(int[] arr, int k) {
            int N = arr.length;
            return process(arr, k, 0, N - 1);
        }

        public int process(int[] arr, int k, int i, int j) {
            if (i > j) return 0;
            if (i == j) return arr[i];
            if (j - i + 1 <= k) {
                int max = Integer.MIN_VALUE;
                for (int ind = i; ind <= j; ind++) {
                    if (max < arr[ind]) max = arr[ind];
                }
                return max * (j - i + 1);
            }
            //from left to right
            int ans = Integer.MIN_VALUE;
            int len = 1;
            int leftMax = arr[i];
            while (len <= k && i + len <= j) {
                ans = Math.max(ans, leftMax * len + process(arr, k, i + len, j));

                leftMax = leftMax < arr[i + len] ? arr[i + len] : leftMax;
                len++;
            }

            return ans;
        }
    }

    class Solution_memory_1D {
        Map<Integer, Integer> cache = new HashMap<>();

        public int maxSumAfterPartitioning(int[] arr, int k) {
            int N = arr.length;
            if (N == 1) return arr[0];
            return process(arr, k, N - 1);
        }

        public int process(int[] arr, int k, int i) {
            if (!cache.containsKey(i)) {
                if (i == 0) {
                    cache.put(0, arr[0]);
                } else if (i + 1 <= k) {
                    //get max and result for subArray [0,i] with length <= k
                    int max = Integer.MIN_VALUE;
                    for (int ind = 0; ind <= i; ind++) {
                        if (max < arr[ind]) max = arr[ind];
                    }
                    cache.put(i, max * (i + 1));
                } else {
                    int ans = Integer.MIN_VALUE;
                    int len = 1;
                    int rightMax = arr[i];
                    while (len <= k && i - len >= 0) {
                        ans = Math.max(ans, rightMax * len + process(arr, k, i - len));

                        rightMax = rightMax < arr[i - len] ? arr[i - len] : rightMax;
                        len++;
                    }
                    cache.put(i, ans);
                }
            }

            return cache.get(i);
        }
    }

    class Solution_dp {
        Map<Integer, Integer> cache = new HashMap<>();
        public int maxSumAfterPartitioning(int[] arr, int k) {
            int N = arr.length;
            if(N == 1) return arr[0];

            int[] dp = new int[N];
            dp[0] = arr[0];
            int leftMax = arr[0];
            for(int i = 1; i < k; i++){
                leftMax = arr[i] > leftMax? arr[i] : leftMax;
                dp[i] = leftMax * (i+1);
            }

            for(int i = k; i < N; i++){
                int ans = Integer.MIN_VALUE;
                int rightMax = arr[i];
                for(int len = 1; len <= k && i-len >= 0; len++){
                    ans = Math.max(ans, rightMax*len + dp[i-len]);
                    rightMax = rightMax < arr[i-len]? arr[i-len]: rightMax;
                }
                dp[i] = ans;
            }

            return dp[N-1];
        }
    }
}
