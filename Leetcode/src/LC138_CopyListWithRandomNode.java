package Leetcode.src;

import java.util.HashMap;
import java.util.Map;

public class LC138_CopyListWithRandomNode {
    class Solution {
        Map<Node, Node> visited = new HashMap<>();

        public Node copyRandomList(Node head) {
            if(head == null) return null;

            Node copy = new Node(head.val);

            if(visited.containsKey(head)){
                return visited.get(head);
            }else{
                visited.put(head,copy);
            }

            copy.next = copyRandomList(head.next);
            copy.random = copyRandomList(head.random);

            return visited.get(head);
        }
    }

    class Solution2 {
        public Node copyRandomList(Node head) {
            if(head == null) return null;

            Node cur = head;
            //A->B->C ---> A->A'->B->B'->C->C'
            while(cur != null){
                Node copy = new Node(cur.val);
                copy.next = cur.next;
                cur.next = copy;
                cur = copy.next;
            }

            cur = head;
            //fill the random info for copied nodes
            while(cur!=null){
                Node copy = cur.next;
                Node random = cur.random;
                if(random != null){
                    copy.random = random.next;
                }
                cur = copy.next;
            }

            Node new_node = head.next;
            cur = head;
            //separate  A->A'->B->B'->C->C' to A->B->C and A'->B'->C'
            while(cur != null){
                Node copy = cur.next;
                cur.next = copy.next;
                cur = cur.next;
                copy.next = cur != null? cur.next: null;
            }

            return new_node;
        }
    }
}
