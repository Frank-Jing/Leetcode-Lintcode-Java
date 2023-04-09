package Leetcode.src.MathBitOther;

public class LC7_ReverseInteger {
    class Solution {
        public int reverse(int x) {
            int ans = 0;
            while(x != 0){
                int numHead = x/10;
                int numTail = x%10;
                // Integer.MAX =  2147483647
                // Integer.MIN = -2147483648
                if(ans > Integer.MAX_VALUE/10 || (ans == Integer.MAX_VALUE/10 && numTail > 7)){
                    return 0;
                }else if(ans < Integer.MIN_VALUE/10 || (ans == Integer.MIN_VALUE/10 && numTail < -8)){
                    return 0;
                }
                ans = ans*10 + numTail;
                x = numHead;
            }
            return ans;
        }
    }
}
