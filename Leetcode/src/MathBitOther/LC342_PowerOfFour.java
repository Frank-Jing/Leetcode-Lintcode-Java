package Leetcode.src.MathBitOther;

public class LC342_PowerOfFour {
    public boolean isPowerOfFour(int n) {
        return n>0 & (n&(n-1)) == 0 & n%3 == 1;
    }
}
