package Leetcode.src.DynamicProblem;

public class LC338_CountingBits {
    class Solution_1 {
        public int[] countBits(int n) {
            int[] ans = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                ans[i] = countOnes(i);
            }
            return ans;
        }

        public int countOnes(int num) {
            int cnt = 0;
            while (num != 0) {
                int mostRightOne = num & (~num + 1);
                num -= mostRightOne;
                cnt++;
            }
            return cnt;
        }
    }

    class Solution_2 {
        public int[] countBits(int n) {
            int[] ans = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                ans[i] = countOnes(i);
            }
            return ans;
        }

        public int countOnes(int num) {
            int cnt = 0;
            int pos = 31;
            while (pos >= 0) {
                cnt += (num >> pos) & 1;
                pos--;
            }
            return cnt;
        }
    }

    public class Solution_3 {
        public int[] countBits(int n) {
            int[] ans = new int[n + 1];
            for (int x = 1; x <= n; ++x) {
                // x / 2 is x >> 1 and x % 2 is x & 1
                ans[x] = ans[x >> 1] + (x & 1);
            }
            return ans;
        }
    }

    public class Solution_4 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int x = 1; x <= num; ++x) {
                ans[x] = ans[x & (x - 1)] + 1;
            }
            return ans;
        }
    }
}
