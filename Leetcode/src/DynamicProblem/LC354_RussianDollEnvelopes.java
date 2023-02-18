package Leetcode.src.DynamicProblem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LC354_RussianDollEnvelopes {
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
                }
            });

            int N = envelopes.length;
            int[] heights = new int[N];
            for (int i = 0; i < N; i++) {
                heights[i] = envelopes[i][1];
            }

//            return lengthOfLIS_2(heights); // did not pass
//            return lengthOfLIS_2(heights); // pass but not efficient
            return lengthOfLIS_3(heights);
        }

        // exceeds time limit
        public int lengthOfLIS(int[] nums) {

            int N = nums.length;
            if (N == 1) return 1;

            int[] dp = new int[N];
            dp[0] = 1;
            for (int i = 1; i < N; i++) {
                int maxSub = 1;
                // the following loop is searching for num[j] that less than target nums[i]
                // could be optimized to use binary search, reduce O(N) to O(logN)
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        maxSub = Math.max(maxSub, dp[j] + 1);
                    }
                }

                dp[i] = maxSub;
            }

            int maxLen = 1;
            for (int i = 0; i < N; i++) {
                maxLen = Math.max(maxLen, dp[i]);
            }

            return maxLen;
        }

        public int lengthOfLIS_2(int[] nums) {
            int N = nums.length;
            if (N == 1) return 1;

            List<Integer> top = new LinkedList<>();
            top.add(nums[0]);
            int right = 0;
            for (int i = 0; i < N; i++) {
                if (nums[i] > top.get(right)) {
                    top.add(nums[i]);
                    right++;
                } else {
                    int l = 0, r = right;
                    while (l < r) {
                        int mid = l + (r - l) / 2;
                        if (top.get(mid) < nums[i]) {
                            l = mid + 1;
                        } else {
                            r = mid;
                        }
                    }
                    top.set(l, nums[i]);
                }
            }

            return right + 1;
        }

        public int lengthOfLIS_3(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
        }
    }
}
