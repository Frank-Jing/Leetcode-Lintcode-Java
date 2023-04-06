package Leetcode.src.Graph;

import java.util.*;

public class LC886_PossibleBipartition {
    class UnionFind {
        public int[] friend;
        public int[] size;

        public UnionFind(int n) {
            friend = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                friend[i] = i;
                size[i] = 1;
            }
        }

        public int findFriend(int i) {
            Deque<Integer> help = new ArrayDeque<>();
            while (friend[i] != i) {
                help.push(i);
                i = friend[i];
            }

            while (!help.isEmpty()) {
                friend[help.pop()] = i;
            }

            return i;
        }

        public void union(int i, int j) {
            int f1 = findFriend(i);
            int f2 = findFriend(j);
            if (f1 == f2) {
            } else {
                if (size[f1] <= size[f2]) {
                    size[f2] += size[f1];
                    friend[f2] = f1;
                } else if (size[f1] > size[f2]) {
                    size[f1] += size[f2];
                    friend[f1] = f2;
                }
            }
        }
    }

    class Solution_UnionFind {
        public boolean possibleBipartition(int n, int[][] dislikes) {
            UnionFind uf = new UnionFind(n);
            Map<Integer, ArrayList<Integer>> nodes = new HashMap<>();
            for (int[] pair : dislikes) {
                int p1 = pair[0];
                int p2 = pair[1];
                if (!nodes.containsKey(p1)) {
                    nodes.put(p1, new ArrayList<Integer>());
                }
                if (!nodes.containsKey(p2)) {
                    nodes.put(p2, new ArrayList<Integer>());
                }
                nodes.get(p1).add(p2);
                nodes.get(p2).add(p1);
            }

            for (int node = 1; node <= n; node++) {
                if (nodes.containsKey(node)) {
                    for (int dislike : nodes.get(node)) {
                        if (uf.findFriend(node) == uf.findFriend(dislike)) {
                            return false;
                        }
                        uf.union(nodes.get(node).get(0), dislike);
                    }
                }
            }

            return true;
        }

    }


    class Solution_DFS {
        public boolean possibleBipartition(int n, int[][] dislikes) {
            if (n <= 2) return true;

            ArrayList<Integer>[] adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<Integer>();
            for (int[] pair : dislikes) {
                int p1 = pair[0];
                int p2 = pair[1];
                adj[p1].add(p2);
                adj[p2].add(p1);
            }

            int[] colors = new int[n + 1];
            Arrays.fill(colors, -1);

            for (int i = 1; i <= n; i++) {
                if (colors[i] == -1) {
                    if (!dfs(i, adj, colors)) {
                        return false;
                    }
                }
            }
            return true;

            // when all the nodes are connected, below soln works
            // but when nodes formed islands, the stack way would not work
            // stack here to simulate the DFS process
            // failed test case:
            // n = 5
            // [[1,2],[3,4],[4,5],[3,5]] // 2 islands here
//            Stack<Integer> stack = new Stack<>();
//            stack.push(dislikes[0][0]);
//            while (!stack.isEmpty()) {
//                int cur = stack.pop();
//                if (colors[cur] == -1) {
//                    colors[cur] = 0; //red
//                }
//                for (int neighbour : adj.get(cur)) {
//                    if (colors[neighbour] == colors[cur]) {
//                        return false;
//                    }
//                    if (colors[neighbour] == -1) {
//                        colors[neighbour] = 1 - colors[cur];
//                        stack.push(cur);
//                        stack.push(neighbour);
//                        break;
//                    }
//                }
//            }
//
//            return true;
        }

        public boolean dfs(int start, ArrayList<Integer>[] adj, int[] colors) {
            colors[start] = 0;

            if (adj[start].size() == 0) return true;

            Stack<Integer> stack = new Stack<>();
            stack.push(start);

            while (!stack.isEmpty()) {
                int cur = stack.pop();
                if (colors[cur] == -1) {
                    colors[cur] = 0; //red
                }
                for (int neighbour : adj[cur]) {
                    if (colors[neighbour] == colors[cur]) {
                        return false;
                    }
                    if (colors[neighbour] == -1) {
                        colors[neighbour] = 1 - colors[cur];
                        stack.push(cur);
                        stack.push(neighbour);
                        break;
                    }
                }
            }
            return true;
        }
    }
}
