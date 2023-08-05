package Leetcode.src.Strings;

public class LC5_LongestPalindrome {
    public String longestPalindrome(String s) {
        //brutal force
        int maxLen = 0;
        int start = 0, end = 0;
        for(int i = 0; i< s.length(); i++){
            int lenSingleCenter = palindromicLen(s, i, i);
            int lenDoubleCenter = palindromicLen(s, i, i+1);
            maxLen = Math.max(lenSingleCenter, lenDoubleCenter);

            if(maxLen > end - start + 1){
                start = i - (maxLen - 1)/2;
                end = i + maxLen/2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int palindromicLen(String s, int center1, int center2){

        int left = center1, right = center2;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }

    //https://www.youtube.com/watch?v=V-sEwsca1ak

}
