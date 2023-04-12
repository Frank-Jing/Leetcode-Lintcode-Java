package Leetcode.src.Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Interview_TwoSigma_MergeNStreams {
    class StreamMerger{
        Stream last;
        PriorityQueue<Stream> heap;
        List<Stream> combined;
        int next;

        public StreamMerger(){
            heap = new PriorityQueue<>((a, b) -> a.time - b.time);
            last = new Stream(0, -1);
            combined = new ArrayList<>();
            next = 0;
        }

        public Stream mergeStreams(Stream[] streamList){
            for(Stream i : streamList){
                if(i != null){
                    heap.offer(i);
                }
            }

            while(!heap.isEmpty()){
                Stream top = heap.poll();
                //pay attention here
                Stream next = top.getNextElement();

                if(next != null){
                    Stream realNext = new Stream(top.val + next.val, next.time);
                    heap.offer(realNext);
                }

                if(combined.size() == 0){
                    combined.add(top);
                    last = top;
                }else{
                    Stream compressed = new Stream(top.val - last.val, top.time);
                    combined.add(compressed);
                    last = top;
                }
            }

            return combined.get(combined.size() - 1);
        }

        public Stream getNextElement(){
            if(next >= combined.size()) return null;
            return combined.get(next++);
        }
    }

    class Stream{
        int val;
        int time;
        Stream(int d, int t){
            this.val = d;
            this.time = t;
        }

        // fake getNext function
        Stream getNextElement(){
            return new Stream(0, 0);
        }
    }

}


