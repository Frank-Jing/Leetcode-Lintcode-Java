package Leetcode.src.Sorting;

import java.util.Arrays;

public class LC252_MeetingRooms {
    class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
            boolean ans = true;
            if(intervals.length <= 1) return ans;

            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            for(int i = 0; i<intervals.length-1; i++){
                if (intervals[i][1] > intervals[i + 1][0]) {
                    return false;
                }
            }
            return ans;
        }
    }
}
