package Leetcode.src.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LC210_CourseSchedule_II {
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[][] adj = new int[numCourses][numCourses];
            Map<Integer, Integer> inMap = new HashMap<>();
            for(int i = 0; i< numCourses; i++){
                inMap.put(i, 0);
            }

            for(int[] prereq : prerequisites){
                int cur = prereq[0];
                int pre = prereq[1];
                adj[pre][cur] = 1;
                inMap.put(cur, inMap.get(cur) + 1);
            }

            Queue<Integer> zeroQueue = new LinkedList<>();
            for(int i = 0; i< numCourses; i++){
                if(inMap.get(i) == 0){
                    zeroQueue.offer(i);
                }
            }

            int[] courseOrder = new int[numCourses];
            int ind = 0;
            while(!zeroQueue.isEmpty()){
                int cur = zeroQueue.poll();
                courseOrder[ind++] = cur;
                for(int i = 0; i < numCourses; i++){
                    if(adj[cur][i] == 1){
                        inMap.put(i, inMap.get(i) - 1);
                        if(inMap.get(i) == 0){
                            zeroQueue.offer(i);
                        }
                    }
                }
            }

            return ind == numCourses? courseOrder : new int[0];
        }
    }
}
