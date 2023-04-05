package Leetcode.src.Sorting;

import java.util.*;

public class LC692_TopKFrequentWords {
    class Solution_minHeap {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> freq = new HashMap<>();
            for(String word : words){
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }

            PriorityQueue<String> q = new PriorityQueue<>(
                    (a,b) -> freq.get(a).equals(freq.get(b))? b.compareTo(a) : freq.get(a) - freq.get(b));

            for(String word : freq.keySet()){
                q.offer(word);
                if(q.size() > k){
                    q.poll();
                }
            }

            LinkedList<String> ans = new LinkedList<>();
            while(q.size() > 0){
                ans.addFirst(q.poll());
            }

            return ans;
        }
    }
}
