package Leetcode.src.Sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC347_TopKFrequentNumber {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int i: nums){
            cnt.put(i, cnt.getOrDefault(i, 0) + 1);
        }

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> cnt.get(b) - cnt.get(a));
        for(int i:cnt.keySet()){
            queue.offer(i);
        }
        int[] res = new int[k];
        int i = 0;
        while(i<k){
            res[i++] = queue.poll();
        }
        return res;
    }
}
