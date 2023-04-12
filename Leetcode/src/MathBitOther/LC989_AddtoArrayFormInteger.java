package Leetcode.src.MathBitOther;

import java.util.LinkedList;
import java.util.List;

public class LC989_AddtoArrayFormInteger {
    // special case num = [0] , k = 10000
    class Solution {
        public List<Integer> addToArrayForm(int[] num, int k) {
            LinkedList<Integer> ans = new LinkedList<>();
            if(k == 0) return ans;

            for(int i = num.length - 1; i>=0 ; i--){
                k += num[i];
                ans.addFirst(k%10);
                k = k/10;
            }
            while(k > 0){
                ans.addFirst(k%10);
                k/=10;
            }
            return ans;
        }
    }
}
