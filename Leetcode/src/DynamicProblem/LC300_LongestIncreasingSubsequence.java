package Leetcode.src.DynamicProblem;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/longest-increasing-subsequence/solutions/1326308/c-python-dp-binary-search-bit-segment-tree-solutions-picture-explain-o-nlogn/
public class LC300_LongestIncreasingSubsequence {
    class Solution_recursion {
        public int lengthOfLIS(int[] nums) {
            int maxLen = 0;
            for (int i = 0; i < nums.length; i++) {
                maxLen = Math.max(maxLen, process(nums, i));
            }

            return maxLen;
        }

        // longest increasing subsequence ended with nums[i]
        public int process(int[] nums, int i) {
            if (i == 0) return 1;

            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, process(nums, j) + 1);
                }
            }

            return max;
        }
    }

    class Solution_dp {
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
    }

    class Solution_patience_sort {
        //patient sort
        public int lengthOfLIS(int[] nums) {
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
    }

    class Solution_patience_sort_2 {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                // key is len HERE!
                // always ensure that the dp[0, len-1] is sorted
                int i = Arrays.binarySearch(dp, 0, len, num);
                // i = -1 mean did not find the value in dp
                // in this case, nums[i] is the smallest, replace the first pile
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                // in this case, expand the pile
                if (i == len) {
                    len++;
                }
            }
            return len;
        }
    }

}
