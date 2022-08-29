package Leetcode.src;

import java.util.HashMap;
import java.util.Map;

public class LC146_LRUCache {
    public class LRUCache {
        Map<Integer, DLinkedNode> cache = new HashMap<>();
        int curSize;
        int capacity;
        DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.curSize = 0;
            this.capacity = capacity;

            this.head = new DLinkedNode();
            this.tail = new DLinkedNode();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            int returnVal = -1;
            if(cache.containsKey(key)){
                DLinkedNode node = cache.get(key);
                moveToHead(node, head);
                returnVal = node.val;
            }
            return returnVal;
        }

        public void put(int key, int value) {
            if(cache.containsKey(key)){
                DLinkedNode nodeUpdate = cache.get(key);
                nodeUpdate.val = value;
                moveToHead(nodeUpdate, head);
                cache.put(key, nodeUpdate);
            }else{
                curSize++;
                DLinkedNode nodeAdd = new DLinkedNode(key, value);
                nodeAdd.key = key;
                nodeAdd.val = value;
                addNode(nodeAdd, head);
                cache.put(key, nodeAdd);

                if(curSize >capacity){
                    DLinkedNode nodeRemove = popTail(tail);
                    cache.remove(nodeRemove.key);
                    curSize--;
                }
            }
        }


        class DLinkedNode {
            DLinkedNode pre;
            DLinkedNode next;
            int key;
            int val;
            DLinkedNode(){}
            DLinkedNode(int key, int val){this.key = key; this.val = val;}
        }

        public void addNode(DLinkedNode node, DLinkedNode head){
            DLinkedNode next = head.next;
            head.next = node;
            node.pre = head;
            next.pre = node;
            node.next = next;
        }

        public void removeNode(DLinkedNode node){
            DLinkedNode pre = node.pre;
            DLinkedNode next = node.next;

            //prevent the loop
            node.pre = null;
            node.next = null;

            pre.next = next;
            next.pre = pre;

        }

        public void moveToHead(DLinkedNode node, DLinkedNode head){
            removeNode(node);
            addNode(node, head);
        }

        public DLinkedNode popTail(DLinkedNode tail){
            DLinkedNode res = tail.pre;
            removeNode(res);
            return res;
        }
    }
}
