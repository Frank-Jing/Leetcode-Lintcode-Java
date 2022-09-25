package Leetcode.src.MathBitOther;

import java.util.HashSet;
import java.util.Set;

public class LC287_FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int[] cnt = new int[nums.length];
        for(int i: nums){
            cnt[i] = cnt[i] + 1;
            if(cnt[i] > 1){
                return i;
            }
        }

        return -1;
    }

    public int findDuplicate2(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int i:nums){
            if(seen.contains(i)){
                return i;
            }
            seen.add(i);
        }

        throw new NullPointerException("wrong input");
    }
}
