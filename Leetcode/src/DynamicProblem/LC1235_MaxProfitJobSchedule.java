package Leetcode.src.DynamicProblem;

import java.util.Arrays;

public class LC1235_MaxProfitJobSchedule {
    class Solution {
        int[] profitMemo;
        int totalLen;
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            totalLen = startTime.length;
            profitMemo = new int[totalLen];
            Arrays.fill(profitMemo, -1);

            Job[] jobList = new Job[totalLen];
            for(int i = 0; i< totalLen; i++){
                Job cur = new Job(startTime[i], endTime[i], profit[i]);
                jobList[i] = cur;
            }
            Arrays.sort(jobList, (a, b) -> a.startTime - b.startTime);

            int ans = process(jobList, 0);

            return ans;
        }

        public int process(Job[] jobList, int index){
            if(index == totalLen){
                return 0;
            }
            if(profitMemo[index] != -1){
                return profitMemo[index];
            }

            // include current, then jump to next valid job
            int nextIndex = findNext(jobList, index);
            int profit1 = jobList[index].profit + process(jobList, nextIndex);

            // ignore current job, jump direct to next
            int profit2 = process(jobList, index + 1);

            int finalProfit =  Math.max(profit1, profit2);
            profitMemo[index] = finalProfit;

            return finalProfit;
        }

        public int findNext(Job[] jobList, int index){
            int l = index;
            int r = totalLen;
            int minNextTime = jobList[index].endTime;
            while(l < r){
                int mid = l + (r-l)/2;
                int midStartTime = jobList[mid].startTime;
                if(midStartTime < minNextTime){
                    l = mid + 1;
                }else{
                    r = mid;
                }
            }
            return l;
        }
    }

    class Job{
        int startTime;
        int endTime;
        int profit;

        public Job(int start, int end, int p){
            this.startTime = start;
            this.endTime = end;
            this.profit = p;
        }
    }
}
