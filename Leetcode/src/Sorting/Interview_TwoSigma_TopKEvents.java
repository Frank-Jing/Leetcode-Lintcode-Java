package Leetcode.src.Sorting;

import java.util.*;

public class Interview_TwoSigma_TopKEvents {
    class Hello {
        public void main(String[] args) {
            TopKEvent eg = new TopKEvent();
            eg.process(1, 2);
            printList(eg.getTopK());
            eg.process(2, 1);
            printList(eg.getTopK());
            eg.process(2, 2);
            printList(eg.getTopK());
            eg.process(3, 1);
            printList(eg.getTopK());
            eg.process(1, 6);
            printList(eg.getTopK());
            eg.process(4, 8);
            printList(eg.getTopK());
            eg.process(5, 1);
            printList(eg.getTopK());
            eg.process(6, 6);
            printList(eg.getTopK());
            eg.process(3, 9);
            printList(eg.getTopK());
        }

        public static void printList(List<Integer> list){
            int n = list.size();
            for(int i = 0; i< n; i++){
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }

    class TopKEvent {
        Map<Integer, Integer> candidate;
        Map<Integer, Integer> boss;
        PriorityQueue<Integer> topHeap;
        private int kLimit = 4;

        public TopKEvent() {
            candidate = new HashMap<>();
            boss = new HashMap<>();
            //pay attention here
            topHeap = new PriorityQueue<>((a, b) -> boss.get(a) - boss.get(b));
        }

        public void process(int i, int v) {
            if (boss.containsKey(i)) {
                boss.put(i, v);
                topHeap.remove(i);
                topHeap.offer(i);
            } else {
                boss.put(i, v);
                topHeap.offer(i);
                if (topHeap.size() > kLimit) {
                    int oldBoss = topHeap.poll();
                    candidate.put(oldBoss, boss.get(oldBoss));
                    boss.remove(oldBoss);
                }
            }
        }

        public List<Integer> getTopK() {
            int i = 0;
            LinkedList<Integer> ans = new LinkedList<>();
            Stack<Integer> temp = new Stack<>();
            while (i < kLimit && !topHeap.isEmpty()) {
                if (!topHeap.isEmpty()) {
                    int id = topHeap.poll();
                    temp.push(id);
                    ans.addFirst(id);
                } else {
                    break;
                }
                i++;
            }
            while (!temp.isEmpty()) {
                topHeap.offer(temp.pop());
            }
            return ans;
        }
    }

}
