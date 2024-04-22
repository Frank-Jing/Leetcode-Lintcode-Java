package Leetcode.src.DFSBFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC1361_ValidateBinaryTreeNodes {
    class Solution {
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            int root = findRoot(n, leftChild, rightChild);
            if(root == -1) return false;
    
            Set<Integer> seen = new HashSet<>();
            Queue<Integer> q = new LinkedList<>();
            q.offer(root);
    
            while(!q.isEmpty()){
                int node = q.poll();
                if(seen.contains(node)){
                    return false;
                }
                seen.add(node);
                if(leftChild[node] != -1){
                    q.offer(leftChild[node]);
                }
                if(rightChild[node] != -1){
                    q.offer(rightChild[node]);
                }
            }
    
            return seen.size() == n;
        }
    
        public int findRoot(int n, int[] leftChild, int[] rightChild){
            Set<Integer> childs = new HashSet<>();
            for(int i : leftChild){
                if(i != -1) childs.add(i);
            }
    
            for(int i : rightChild){
                if(i != -1) childs.add(i);
            }
    
            for(int i = 0; i < n; i++){
                if(!childs.contains(i)) return i;
            }
    
            return -1;
    
        }
    }
}
