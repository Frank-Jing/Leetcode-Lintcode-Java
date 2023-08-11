package Leetcode.src.Backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17_LetterCombinationsPhoneNumber {
    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> results = new ArrayList<>();
            if(digits.length() == 0) return results;

            Map<Character, String> phoneMap = new HashMap<>();
            phoneMap.put('2', "abc");
            phoneMap.put('3', "def");
            phoneMap.put('4', "ghi");
            phoneMap.put('5', "jkl");
            phoneMap.put('6', "mno");
            phoneMap.put('7', "pqrs");
            phoneMap.put('8', "tuv");
            phoneMap.put('9', "wxyz");

            int n = digits.length();

            backtrack(0, digits, new char[n], results, phoneMap);

            return results;
        }

        public void backtrack(int curInd,
                              String digits,
                              char[] curComb,
                              List<String> results,
                              Map<Character, String> phoneMap){
            if(curInd == digits.length()){
                results.add(new String(curComb));
                return;
            }
            String numToChar = phoneMap.get(digits.charAt(curInd));
            for(char c : numToChar.toCharArray()){
                curComb[curInd] = c;
                backtrack(curInd+1, digits, curComb, results, phoneMap);
                // curComb[curInd] = '';
            }

            return;
        }
    }
}
