package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class LC1650_LCAofBinaryTree_III {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    class Solution {
        public Node lowestCommonAncestor(Node p, Node q) {
            List<Node> path = find_Path(p);
            while (q.parent != null) {
                for (Node node : path) {
                    if (node == q) return q;
                }
                q = q.parent;
            }
            return q;
        }

        public List<Node> find_Path(Node p) {
            List<Node> path = new ArrayList<>();
            while (p.parent != null) {
                path.add(p);
                p = p.parent;
            }
            return path;
        }
    }

    //similar to LC160-Intersection of Two Linked Lists
    class Solution_2 {
        public Node lowestCommonAncestor(Node p, Node q) {
            Node a = p, b = q;
            while(a != b){
                a = a == null? q : a.parent;
                b = b == null? p : b.parent;
            }

            return a;
        }
    }

}
