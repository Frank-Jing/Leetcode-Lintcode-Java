package Leetcode.src.BinaryTree;

import java.util.HashSet;
import java.util.List;

public class LC1506_FindNaryTreeRoot {
    class Solution {
        public Node findRoot(List<Node> tree) {
            HashSet<Integer> seen = new HashSet<>();
            for(Node cur : tree){
                if(cur.children != null){
                    for(Node child : cur.children){
                        seen.add(child.val);
                    }
                }
            }

            for(Node cur : tree){
                if(!seen.contains(cur.val)){
                    return cur;
                }
            }
            return null;
        }
    }

    class Solution_visitOnce {
        public Node findRoot(List<Node> tree) {
            int sum = 0;
            for(Node cur : tree){
                sum += cur.val;
                if(cur.children != null){
                    for(Node child : cur.children){
                        sum -= child.val;
                    }
                }
            }

            for(Node cur : tree){
                if(cur.val == sum){
                    return cur;
                }
            }
            return null;
        }
    }
}
