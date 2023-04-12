package Leetcode.src.MathBitOther;

public class LC415_AddStrings {
    class Solution {
        public String addStrings(String num1, String num2) {
            int n = num1.length();
            int m = num2.length();
            if(num1.equals("0")) return num2;
            if(num2.equals("0")) return num1;

            int k = 0;
            int i = n-1, j = m-1;
            StringBuilder ans = new StringBuilder();
            while(i>=0 || j>=0 || k>0){
                int dig1 = 0;
                if(i >= 0) dig1 = (int)(num1.charAt(i--) - '0');
                int dig2 = 0;
                if(j >= 0) dig2 = (int)(num2.charAt(j--) - '0');
                k += dig1 + dig2;
                ans.append((char)(k%10 + '0'));
                k /= 10;
            }
            return ans.reverse().toString();
        }
    }
}
