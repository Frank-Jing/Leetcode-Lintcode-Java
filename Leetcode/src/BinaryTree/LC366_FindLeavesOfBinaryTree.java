package Leetcode.src.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class LC366_FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {

        if(root == null) return null;
        List<List<Integer>> leftList = findLeaves(root.left);
        List<List<Integer>> rightList = findLeaves(root.right);

        List<List<Integer>> ans = merge(root, leftList, rightList);

        return ans;
    }

    public List<List<Integer>> merge(TreeNode head, List<List<Integer>> list1, List<List<Integer>> list2){
        List<Integer> rootVal = new ArrayList<Integer>();
        rootVal.add(head.val);
        List<List<Integer>> ans = new ArrayList<>();
        if(list1 == null && list2 == null){
            ans.add(rootVal);
            return ans;
        }
        if(list1 == null){
            list2.add(rootVal);
            ans = list2;
        }else if(list2 == null){
            list1.add(rootVal);
            ans = list1;
        }else{
            //make sure list1 is longer
            List<List<Integer>> temp = new ArrayList<>();
            if(list1.size() < list2.size()){
                temp = list2;
                list2 = list1;
                list1 = temp;
            }

            for(int i=0; i < list2.size(); i++){
                List<Integer> nodes = list2.get(i);
                list1.get(i).addAll(nodes);
            }

            list1.add(rootVal);
            ans = list1;
        }

        return ans;
    }
}
