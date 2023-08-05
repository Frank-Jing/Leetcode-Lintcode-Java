package Leetcode.src.MathBitOther;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC15_3Sum {
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new LinkedList<>();
            int l = 1, r = nums.length-1;
            for(int i = 0; i < nums.length; i++){
                int cur = nums[i];
                l = i+1;
                r = nums.length - 1;

                while(l < r){
                    int left = nums[l], right = nums[r];
                    int sum = cur + left + right;
                    if(sum == 0){
                        ArrayList<Integer> record = new ArrayList<>(Arrays.asList(cur, left, right));
                        res.add(record);
                        // avoid duplicate
                        while(l < r && nums[l] == left){
                            l++;
                        }
                        while(l < r && nums[r] == right){
                            r--;
                        }
                    }else if(sum < 0){
                        // avoid duplicate
                        while(l < r && nums[l] == left){
                            l++;
                        }
                    }else{
                        // avoid duplicate
                        while(l < r && nums[r] == right){
                            r--;
                        }
                    }
                }
                // avoid duplicate
                while(i < nums.length-1 && nums[i+1] == cur){
                    i++;
                }

            }

            return res;
        }
    }
}
