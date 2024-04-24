package Leetcode.src.BinaryTree;

import java.util.*;

public class LC987_BinaryTreeVerticalTraverse {
    class Solution_DFS {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1]? a[0] - b[0] : a[1] - b[1]);
        Map<String, List<Integer>> vals = new HashMap<>();
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            traverse(root, 0 , 0);

            int[] prevPos = pq.peek();
            ans.add(new ArrayList<>());

            while(!pq.isEmpty()){
                int[] curPos = pq.poll();
                if(curPos[1] != prevPos[1]){
                    ans.add(new ArrayList<>());
                }
                List<Integer> verticalVals = vals.get(posToString(curPos));
                Collections.sort(verticalVals);
                // verticalVals.sort(Comparator.naturalOrder());
                ans.get(ans.size() - 1).addAll(verticalVals);
                prevPos = curPos;
            }
            return ans;
        }

        public void traverse(TreeNode node, int level, int col){
            if(node == null) return;
            int[] pos = new int[]{level, col};
            String posStr = posToString(pos);
            // because the two int[] aren't equal
            if(!vals.containsKey(posStr)){
                vals.put(posStr, new ArrayList<>());
                pq.offer(pos);
            }

            List<Integer> record = vals.get(posStr);
            record.add(node.val);
            vals.put(posStr, record);

            traverse(node.left, level + 1, col - 1);
            traverse(node.right, level + 1, col + 1);
            return;
        }

        public String posToString(int[] pos){
            StringBuilder s = new StringBuilder();
            s.append(pos[0]);
            s.append(",");
            s.append(pos[1]);
            return s.toString();
        }
    }
}
