package Leetcode.src.MathBitOther;

public class LC67_AddBinary {
    class Solution {
        public String addBinary(String a, String b) {
            int n = a.length();
            int m = b.length();
            if(a.equals("0")) return b;
            if(b.equals("0")) return a;

            int k = 0;
            int i = n-1, j = m-1;
            StringBuilder ans = new StringBuilder();
            while(i>=0 || j>=0 || k>0){
                int dig1 = 0;
                if(i >= 0) dig1 = (int)(a.charAt(i--) - '0');
                int dig2 = 0;
                if(j >= 0) dig2 = (int)(b.charAt(j--) - '0');
                k += dig1 + dig2;
                ans.append((char)(k%2 + '0'));
                k /= 2;
            }
            return ans.reverse().toString();
        }
    }
}
