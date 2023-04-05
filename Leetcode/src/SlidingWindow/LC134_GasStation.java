package Leetcode.src.SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;

public class LC134_GasStation {
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int N = gas.length;
            int total = 0, gasAviable = 0;
            int index = 0;
            for(int i = 0; i < N; i++){
                total += gas[i] - cost[i];
                gasAviable += gas[i] - cost[i];

                if(gasAviable < 0){
                    index = i + 1;
                    gasAviable = 0;
                }
            }

            return total < 0 ? -1 : index;
        }
    }
    class Solution_MinDeque {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            boolean[] good = checkGas(gas, cost);
            for(int i = 0; i < gas.length; i++){
                if(good[i] == true){
                    return i;
                }
            }

            return -1;
        }

        public boolean[] checkGas(int[] gas, int[] cost){
            int N = gas.length;
            int M = (N<<1);
            int[] presum = new int[M];
            for(int i = 0; i < N; i++){
                presum[i] = gas[i] - cost[i];
                presum[i+N] = gas[i] - cost[i];
            }
            for (int i = 1; i < M; i++) {
                presum[i] += presum[i - 1];
            }
            // index of min
            Deque<Integer> q = new LinkedList<>();
            for(int i = 0; i< N; i++){
                while(!q.isEmpty() && presum[q.peekLast()] >= presum[i]){
                    q.pollLast();
                }
                q.addLast(i);
            }

            boolean[] ans = new boolean[N];
            for(int offset = 0, i = 0, j= N; j<M; offset = presum[i++], j++){
                if(presum[q.peekFirst()] - offset >=0 ){
                    ans[i] = true;
                }
                if(q.peekFirst() == i){
                    q.pollFirst();
                }
                while(!q.isEmpty() && presum[q.peekLast()] >= presum[j]){
                    q.pollLast();
                }
                q.addLast(j);
            }
            return ans;
        }
    }
}
