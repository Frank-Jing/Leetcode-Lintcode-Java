package Leetcode.src.Sorting;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class LC1779_NearestPointWithSameXorY {
    class City{
        int x;
        int y;
        int ind;
        public City(int x, int y, int i){
            this.x = x;
            this.y = y;
            this.ind = i;
        }
    }

    class Solution_heap {
        public int nearestValidPoint(int x, int y, int[][] points) {
            PriorityQueue<City> heap = new PriorityQueue<>(
                    (a, b) -> Math.abs(a.x - x) + Math.abs(a.y - y) == Math.abs(b.x - x) + Math.abs(b.y - y)? a.ind - b.ind :  Math.abs(a.x - x) + Math.abs(a.y - y) - Math.abs(b.x - x) - Math.abs(b.y - y)
            );

            Set<Integer> xs = new HashSet<>();
            Set<Integer> ys = new HashSet<>();

            for(int i = 0; i < points.length; i++){
                int x_cord = points[i][0], y_cord = points[i][1];
                City city = new City(x_cord, y_cord, i);
                heap.add(city);
                xs.add(x_cord);
                ys.add(y_cord);
            }

            int ans = -1;
            if(xs.contains(x) || ys.contains(y)){
                while(!heap.isEmpty()){
                    City city = heap.poll();
                    if(city.x != x && city.y != y){
                        continue;
                    }else{
                        ans = city.ind;
                        break;
                    }
                }
            }

            return ans;
        }
    }

    class Solution {
        public int nearestValidPoint(int x, int y, int[][] points) {
            int ans = Integer.MAX_VALUE;
            int ind = -1;
            int n = points.length;
            for(int i =0;i<n;i++){
                if(x==points[i][0] || y==points[i][1]){
                    int dist = Math.abs(x-points[i][0])+Math.abs(y-points[i][1]);
                    if(ans > dist){
                        ans = dist;
                        ind = i;
                    }
                }
            }

            return ind;
        }
    }
}
