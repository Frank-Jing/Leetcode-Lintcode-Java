package Leetcode.src.MathBitOther;

public class LC231_PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n&n-1) == 0;
    }

    public boolean isPowerOfTwo_2(int n) {
        if(n == 0) return false;

        while(n%2 == 0){
            n = n>>1;
        }

        return n == 1;
    }
}
