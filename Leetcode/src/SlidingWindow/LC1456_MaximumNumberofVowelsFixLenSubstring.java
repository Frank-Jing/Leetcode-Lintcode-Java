package Leetcode.src.SlidingWindow;

public class LC1456_MaximumNumberofVowelsFixLenSubstring {
    class Solution {
        public int maxVowels(String s, int k) {
            int cnt = 0;
            int[] vows = new int['z' + 1];
            vows['a'] = 1;
            vows['e'] = 1;
            vows['i'] = 1;
            vows['o'] = 1;
            vows['u'] = 1;

            int ind = 0;
            for(; ind < k; ind++){
                cnt += vows[s.charAt(ind)];
            }
            int max = cnt;
            while(ind < s.length()){
                cnt += vows[s.charAt(ind)];
                cnt -= vows[s.charAt(ind - k)];
                ind++;
                max = Math.max(max, cnt);
                if(max == k) break;
            }

            return max;
        }
    }
}
