package Leetcode.src.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC253_MeetingRooms_II {
    class Solution {
        public int minMeetingRooms(int[][] intervals) {
            if(intervals.length <= 1) return intervals.length;
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            Queue<Integer> heap = new PriorityQueue<>();
            int max = 0;
            for(int i = 0; i < intervals.length; i++){
                if(!heap.isEmpty() && heap.peek() <= intervals[i][0]){
                    heap.poll();
                }
                heap.add(intervals[i][1]);
                max = Math.max(max, heap.size());
            }

            return max;
        }
    }
}
