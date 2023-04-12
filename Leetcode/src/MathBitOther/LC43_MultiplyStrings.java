package Leetcode.src.MathBitOther;

public class LC43_MultiplyStrings {
    class Solution {
        public String multiply(String num1, String num2) {
            if(num1.equals("0") || num2.equals("0")) return "0";
            StringBuilder number1 = new StringBuilder(num1);
            StringBuilder number2 = new StringBuilder(num2);
            number1.reverse();
            number2.reverse();

            int n = number1.length();
            int m = number2.length();
            StringBuilder res = new StringBuilder();
            for(int i  = 0; i<n+m; i++){
                res.append('0');
            }

            for(int i = 0; i<n; i++){
                for(int j = 0; j<m; j++){
                    int dig1 = (int)(number1.charAt(i) - '0');
                    int dig2 = (int)(number2.charAt(j) - '0');
                    int valCur = (int)(res.charAt(i+j) - '0') + dig1*dig2;
                    res.setCharAt(i+j, (char)(valCur%10 + '0'));
                    int valNext = (int)(res.charAt(i+j+1) - '0') + valCur/10;
                    res.setCharAt(i+j+1, (char)(valNext + '0'));
                }
            }
            if(res.charAt(n+m-1) == '0'){
                res.deleteCharAt(n+m-1);
            }
            res.reverse();

            return res.toString();
        }
    }

    class Solution_II {
        public String multiply(String num1, String num2) {
            if(num1.equals("0") || num2.equals("0")) return "0";
            int n = num1.length();
            int m = num2.length();
            int[] res = new int[n+m];

            for(int i = n-1; i>=0; i--){
                for(int j = m-1; j>= 0; j--){
                    int ind = n-1-i + m-1-j;
                    int sum = res[ind] + (int)(num1.charAt(i) - '0')*(int)(num2.charAt(j) - '0');
                    res[ind] = sum%10;
                    res[ind+1] += sum/10;
                }
            }

            StringBuilder ans = new StringBuilder();
            for(int i = m+n-1; i>=0; i--){
                ans.append((char)(res[i] + '0'));
            }
            if(ans.charAt(0) == '0') ans.deleteCharAt(0);

            return ans.toString();
        }
    }
}
