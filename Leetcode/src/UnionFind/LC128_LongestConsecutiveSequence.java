package Leetcode.src.UnionFind;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC128_LongestConsecutiveSequence {
    class Solution_Heap {
        public int longestConsecutive(int[] nums) {
            if(nums == null || nums.length == 0) return 0;
            if(nums.length == 1) return 1;
            Queue<Integer> heap = new PriorityQueue<>();
            for(int num : nums){
                heap.offer(num);
            }

            int max = 0;
            int start = heap.poll();
            int cnt = 1;
            while(!heap.isEmpty()){
                int next = heap.poll();
                if(next == start+1){
                    cnt++;
                }else if(next > start + 1){
                    max = Math.max(max, cnt);
                    cnt = 1;
                }
                start = next;
            }
            return Math.max(max, cnt);
        }
    }

    class Solution_unionFind {
        public int longestConsecutive(int[] nums) {
            if(nums == null || nums.length == 0) return 0;
            if(nums.length == 1) return 1;

            UnionFind uf = new UnionFind(nums);
            for(int i=0; i< nums.length; i++){
                uf.union(nums[i], nums[i] - 1);
                uf.union(nums[i], nums[i] + 1);
            }

            return uf.max;
        }
    }

    class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> size;
        int max;

        public UnionFind (int[] nums){
            int len = nums.length;
            parent = new HashMap<>();
            size = new HashMap<>();
            max = 1;
            for(int i = 0; i<len; i++){
                parent.put(nums[i], nums[i]);
                size.put(nums[i], 1);
            }
        }

        public int findFather(int num){
            while(parent.get(num) != num){
                num = parent.get(num);
            }
            return num;
        }

        public void union (int num1, int num2){

            if (parent.containsKey(num1) && parent.containsKey(num2)){
                int f1 = findFather(num1);
                int f2 = findFather(num2);

                if(f1 != f2){
                    int size1 = size.get(f1);
                    int size2 = size.get(f2);
                    int newSize = size1 + size2;
                    max = Math.max(max, size1 + size2);
                    if(f1 > f2){
                        parent.put(f1, f2);
                        size.put(f2, newSize);
                        size.remove(f1);
                    }else{
                        parent.put(f2, f1);
                        size.put(f1, newSize);
                        size.remove(f2);
                    }
                }
            }
        }

    }
}
