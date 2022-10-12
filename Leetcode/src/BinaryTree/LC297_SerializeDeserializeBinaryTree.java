package Leetcode.src.BinaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC297_SerializeDeserializeBinaryTree {
    class solution{
        public class Codec {

            // Encodes a tree to a single string.
            public String serialize(TreeNode root) {
                List<String> list = new LinkedList<>();
                preSerial(root, list);
                return String.join(",", list);
            }

            public void preSerial(TreeNode head, List<String> list){
                if(head == null){
                    list.add(null);
                }else{
                    list.add(String.valueOf(head.val));
                    preSerial(head.left, list);
                    preSerial(head.right, list);
                }

            }


            // Decodes your encoded data to tree.
            public TreeNode deserialize(String data) {
                String[] list = data.split(",");
                Queue<String> nodes = new LinkedList<>(Arrays.asList(list));

                return deSerial(nodes);
            }

            public TreeNode deSerial(Queue<String> nodes){
                String val = nodes.poll();
                if(val.equals("null")){
                    return null;
                }

                TreeNode head = new TreeNode(Integer.parseInt(val));
                head.left = deSerial(nodes);
                head.right = deSerial(nodes);
                return head;
            }
        }
    }
}
