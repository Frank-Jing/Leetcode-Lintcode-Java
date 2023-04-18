package Leetcode.src.SlidingWindow;

import java.util.LinkedList;

public class LC904_FruitIntoBaskets {
    class Solution {
        public int totalFruit(int[] fruits) {
            int max = 0;
            // before: when there is a 3rd type added in, poll the queue to maintain
            // now the queue first is the last index of 2nd type of tree
            // that last index - before is the length of 1st and 2nd type length
            // after that update the before to get the last index of 2nd type
            int before = -1;
            int n = fruits.length;
            LinkedList<Integer> qmax = new LinkedList<>();
            for(int R = 0; R < n; R++){
                // always maintain a deque of size 2
                while(!qmax.isEmpty() && fruits[qmax.peekLast()] == fruits[R]){
                    qmax.pollLast();
                }
                qmax.addLast(R);
                if(qmax.size() > 2){
                    // case [1,2, 1]
                    if(fruits[qmax.peekFirst()] == fruits[qmax.peekLast()]){
                        qmax.pollFirst();
                    }else{
                        int ind = qmax.pollFirst();
                        max = Math.max(qmax.peekFirst() - before, max);
                        before = ind;
                    }
                }
            }
            //when case [0,1,2,3,3,3,3,3]
            // before now is 1 which is index of 1
            // qmax.oeekLast is the index of last 3
            // need to check in case we miss the max length contains 2 and 3 type fruits
            max = Math.max(max, qmax.peekLast() - before);

            return max;
        }
    }
}
