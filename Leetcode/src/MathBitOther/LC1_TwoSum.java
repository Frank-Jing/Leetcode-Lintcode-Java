package Leetcode.src.MathBitOther;

import java.util.HashMap;
import java.util.Map;

public class LC1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);

        int[] res = new int[2];

        for(int i = 0; i<nums.length; ++i){
            if(map.containsKey(target - nums[i])){
                res[0] = i;
                res[1] = map.get(target - nums[i]);
                break;
            }else{
                map.put(nums[i], i);
            }
        }
        return res;
    }

    public int[] twoSum_II(int[] nums, int target) {
        Map<Integer, Integer> numInd = new HashMap<>();
        int[] ans = new int[2];
        for(int i = 0; i < nums.length; i++){
            if(numInd.containsKey(target - nums[i])){
                ans = new int[]{numInd.get(target - nums[i]), i};
                break;
            }
            numInd.put(nums[i], i);
        }

        return ans;
    }

}
