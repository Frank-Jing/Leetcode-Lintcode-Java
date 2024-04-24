package Leetcode.src.DFSBFS;

import java.util.*;

public class LC815_BusRoutes {
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if(target == source) return 0;
            // construct route-stop map
            Map<Integer, Set<Integer>> stopRouteMap = new HashMap<>();
            for(int i=0; i < routes.length; i++){
                for(int stop : routes[i]){
                    Set<Integer> stopRouteSet = stopRouteMap.getOrDefault(stop, new HashSet<Integer>());
                    stopRouteSet.add(i);
                    stopRouteMap.put(stop, stopRouteSet);
                }
            }

            if(!stopRouteMap.containsKey(source) || !stopRouteMap.containsKey(target)) return -1;

            // BFS from source's route - other stops - linked route - other stops ...
            // use visted to avoid cycle
            Queue<Integer> q = new LinkedList<>();
            Set<Integer> visitedRoute = new HashSet<>();

            for(int route : stopRouteMap.get(source)){
                q.offer(route);
            }
            int level = 1;

            while(!q.isEmpty()){
                int qSize = q.size();

                for(int i = 0; i < qSize; i++){
                    int curRoute = q.poll();
                    for(int stop : routes[curRoute]){
                        if(stop == target) return level;
                        for(int nextRoute : stopRouteMap.get(stop)){
                            if(!visitedRoute.contains(nextRoute)){
                                visitedRoute.add(nextRoute);
                                q.add(nextRoute);
                            }
                        }
                    }
                }
                level++;
            }

            return -1;
        }
    }

    class Solution_II {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if (source == target) return 0;
            int n = routes.length;
            for (int i = 0; i < n; i++) {
                Arrays.sort(routes[i]);
            }
            Set<Integer> seen = new HashSet<>();
            Set<Integer> targets = new HashSet<>();
            Queue<Integer> queue = new ArrayDeque<>();

            // use sorted array to check if source/target is in each route
            // avoid construction of stop-routes HashMap
            for (int i = 0; i < n; i++) {
                if (Arrays.binarySearch(routes[i], source) >= 0) {
                    seen.add(i);
                    queue.offer(i);
                }
                if (Arrays.binarySearch(routes[i], target) >= 0) {
                    targets.add(i);
                }
            }
            int buses = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int node = queue.poll();
                    if (targets.contains(node)) return buses;
                    for (int j = 0; j < n; j++) {
                        if (seen.contains(j)) continue;
                        if (intersect(routes[node], routes[j])) {
                            seen.add(j);
                            queue.offer(j);
                        }
                    }
                }
                buses++;
            }
            return -1;
        }

        private boolean intersect(int[] route1, int[] route2) {
            int i = 0, j = 0;
            while (i < route1.length && j < route2.length) {
                if (route1[i] == route2[j]) return true;
                if (route1[i] < route2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return false;
        }
    }

}
