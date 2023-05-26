package Leetcode.src.MathBitOther;

import java.util.LinkedList;
import java.util.List;

public class LC2215_FindDifferenceofTwoArrays {
    class Solution {
        public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
            List<Integer> ans1 = getDiff(nums1, nums2);
            List<Integer> ans2 = getDiff(nums2, nums1);

            List<List<Integer>> ans = new LinkedList<>();
            ans.add(ans1);
            ans.add(ans2);

            return ans;
        }

        public List<Integer> getDiff(int[] nums1, int[] nums2){
            List<Integer> ans = new LinkedList<>();
            boolean[] set = new boolean[2001];

            for(int i : nums2){
                set[i+1000] = true;
            }

            for(int i : nums1){
                if(!set[i+1000]){
                    ans.add(i);
                    set[i+1000] = true;
                }
            }

            return ans;
        }
    }
}
