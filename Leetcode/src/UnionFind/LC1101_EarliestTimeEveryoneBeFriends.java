package Leetcode.src.UnionFind;

import java.util.Arrays;

public class LC1101_EarliestTimeEveryoneBeFriends {
    class Solution {
        public int earliestAcq(int[][] logs, int n) {
            UnionFind uf = new UnionFind(n);
            int ans = -1;
            Arrays.sort(logs, (a, b) -> a[0] - b[0]);
            for (int[] log : logs) {
                int time = log[0];
                int p1 = log[1];
                int p2 = log[2];
                uf.union(p1, p2);
                if (uf.size == 1) {
                    ans = time;
                    break;
                }
            }
            return ans;
        }

        class UnionFind {
            int[] parent;
            int size;

            UnionFind(int n) {
                parent = new int[n];
                size = n;
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int findFather(int i) {
                while (parent[i] != i) {
                    i = parent[i];
                }
                return i;
            }

            public void union(int x, int y) {
                int a = findFather(x);
                int b = findFather(y);
                if (a == b) return;
                if (a < b) {
                    parent[b] = a;
                } else {
                    parent[b] = a;
                }
                this.size--;
            }
        }
    }
}
