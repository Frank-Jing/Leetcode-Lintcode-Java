package Leetcode.src.DFSBFS;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC339_NestedListWeightSum {

    
     // This is the interface that allows for creating nested lists.
     // You should not implement it, or speculate about its implementation
     public interface NestedInteger {
     // Constructor initializes an empty nested list.
        // public NestedInteger(){};
     
        // Constructor initializes a single integer.
        // public NestedInteger(int value){};
        
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        
        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);
        
        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);
        
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
     }

     class Solution_BFS {
        public int depthSum(List<NestedInteger> nestedList) {
            int level = 1;
            int sum = 0;
    
            Queue<NestedInteger> q = new LinkedList<>();
            q.addAll(nestedList);
    
            while(!q.isEmpty()){
                int curLevelCnt = q.size();
                for(int i = 0; i < curLevelCnt; i++){
                    NestedInteger obj = q.poll();
                    if(obj.isInteger()){
                        sum += obj.getInteger() * level;
                    }else{
                        q.addAll(obj.getList());
                    }
                }
                level++;
            }
            return sum;
        }
    }
      
    class Solution_DFS {
        int sum = 0;
    
        public int depthSum(List<NestedInteger> nestedList) {
            
            dfs(nestedList, 1);
            return sum;
        }
    
        public void dfs(List<NestedInteger> nestedList, int level){
            for(NestedInteger entry : nestedList){
                if(entry.isInteger()){
                    sum += entry.getInteger() * level;
                }else{
                    dfs(entry.getList(), level + 1);
                }
            }
        }
    
    }
}
