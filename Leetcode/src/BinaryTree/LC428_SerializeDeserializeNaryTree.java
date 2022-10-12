package Leetcode.src.BinaryTree;

import java.util.*;

public class LC428_SerializeDeserializeNaryTree {
    class solution{
        class Codec {
            // Encodes a tree to a single string.
            public String serialize(Node root) {
                String ans = "";
                if(root != null){
                    Queue<Node> queue = new LinkedList<>();
                    queue.offer(root);

                    while(!queue.isEmpty()){
                        int queueSize = queue.size();
                        for(int i = 0; i<queueSize;i++){
                            Node cur = queue.poll();
                            ans += String.valueOf(cur.val);
                            ans += "#";
                            ans += String.valueOf(cur.children.size());
                            ans += "#";
                            if(cur.children != null){
                                queue.addAll(cur.children);
                            }
                        }
                    }
                }
                return ans;
            }

            class NodeInfo{
                Node node;
                int childSize;
                public NodeInfo(int val, int size){
                    this.node = new Node(val);
                    this.childSize = size;
                }
            }

            // Decodes your encoded data to tree.
            public Node deserialize(String data) {
                if (data == "") return null;

                String[] vals = data.split("#");
                Queue<String> levelList = new LinkedList<>(Arrays.asList(vals));

                int val = Integer.parseInt(levelList.poll());
                int childSize = Integer.parseInt(levelList.poll());
                NodeInfo head = new NodeInfo(val, childSize);

                Queue<NodeInfo> queue = new LinkedList<>();
                queue.offer(head);
                while (!queue.isEmpty()) {
                    NodeInfo cur = queue.poll();
                    Node curNode = cur.node;
                    int size = cur.childSize;
                    List<Node> level = new LinkedList<>();

                    if (size != 0) {
                        for (int i = 0; i < size; i++) {
                            val = Integer.parseInt(levelList.poll());
                            childSize = Integer.parseInt(levelList.poll());
                            NodeInfo child = new NodeInfo(val, childSize);
                            queue.add(child);
                            level.add(child.node);
                        }
                    }
                    curNode.children = level;
                }

                return head.node;
            }
        }
    }

    class solution_preorder_recursive{
        class Codec {

            // Encodes a tree to a single string.
            public String serialize(Node root) {
                List<String> list=new LinkedList<>();
                serializeHelper(root,list);
                return String.join(",",list);
            }

            private void serializeHelper(Node root, List<String> list){
                if(root==null){
                    return;
                }else{
                    list.add(String.valueOf(root.val));
                    list.add(String.valueOf(root.children.size()));
                    for(Node child:root.children){
                        serializeHelper(child,list);
                    }
                }
            }

            // Decodes your encoded data to tree.
            public Node deserialize(String data) {
                if(data.isEmpty())
                    return null;

                String[] ss=data.split(",");
                Queue<String> q=new LinkedList<>(Arrays.asList(ss));
                return deserializeHelper(q);
            }

            private Node deserializeHelper(Queue<String> q){
                Node root=new Node();
                root.val=Integer.parseInt(q.poll());
                int size=Integer.parseInt(q.poll());
                root.children=new ArrayList<Node>(size);
                for(int i=0;i<size;i++){
                    root.children.add(deserializeHelper(q));
                }
                return root;
            }
        }
    }
}
