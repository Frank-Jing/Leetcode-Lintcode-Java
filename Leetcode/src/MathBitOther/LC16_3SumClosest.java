package Leetcode.src.MathBitOther;

import java.util.Arrays;

public class LC16_3SumClosest {

    // TLE
    class Solution_TLE {
        int dist;
        int res;
        public int threeSumClosest(int[] nums, int target) {
            dist = Integer.MAX_VALUE;
            res = Integer.MAX_VALUE;
            process(nums, 0, 0, 0, target);

            return res;
        }

        public void process(int[] nums, int cur, int selected, int sum, int target){
            if(selected == 3){
                if(dist > Math.abs(target - sum)){
                    dist = Math.abs(target - sum);
                    res = sum;
                }
                return;
            }
            if(cur == nums.length) return;

            process(nums, cur+1, selected, sum, target);
            process(nums, cur+1, selected+1, sum + nums[cur], target);

            return;
        }
    }

    class Solution_II {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int res = Integer.MAX_VALUE;
            int dist = Integer.MAX_VALUE;

            int l = 1, r = nums.length-1;
            for(int i = 0; i < nums.length; i++){
                int cur = nums[i];
                l = i+1;
                r = nums.length - 1;

                while(l < r){
                    int left = nums[l], right = nums[r];
                    int sum = cur + left + right;
                    if(target == sum){
                        return sum;
                    }else{
                        // avoid duplicate
                        if(dist > Math.abs(target - sum)){
                            dist = Math.abs(target - sum);
                            res = sum;
                        }
                        while(target > sum && l < r && nums[l] == left){
                            l++;
                        }

                        while(target < sum && l < r && nums[r] == right){
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
