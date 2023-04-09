package Leetcode.src.MathBitOther;

import java.util.HashMap;
import java.util.Map;

public class LC3_LongestSubstring {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> tab = new HashMap<>();
            int maxLen = 0;
            int index = 0;
            int start = 0;

            for(Character c: s.toCharArray()){
                if(tab.containsKey(c)){
                    //eg: "abba" the last 'a' would refresh start = tab.get()
                    //to avoid this, use max to ensure the substr starts from position in last round
                    start = Math.max(tab.get(c) + 1, start);
                    tab.replace(c, index);
                }else{
                    tab.put(c, index);
                }
                maxLen = Math.max(maxLen, index - start + 1);
                index++;
            }

            return maxLen;
        }
    }
}
