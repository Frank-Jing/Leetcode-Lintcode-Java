package Leetcode.src.MathBitOther;

public class LC263_UglyNumber {
    class Solution {
        public boolean isUgly(int n) {
            if(n == 1) return true;
            for(int i : new int[]{5,3,2}){
                while(n >= i && n%i == 0){
                    n /= i;
                }
            }

            return n == 1;
        }
    }
}
