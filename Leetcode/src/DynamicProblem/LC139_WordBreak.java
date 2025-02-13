package Leetcode.src.DynamicProblem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC139_WordBreak {
    class Solution_dp {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length()+1];
            dp[0] = true; //{} is always true

            for(int i = 1; i<= s.length(); i++){
                for(int j = 0; j<i; j++){
                    if(dp[j] && dict.contains(s.substring(j,i))){
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }
    }

    class Solution_topSoln_memory {
        public boolean wordBreak(String s, List<String> wordDict) {
            //return recWay2(s, wordDict);
            return recWay1(s, wordDict);
        }

        boolean recWay2(String s, List<String> wordDict) {
            Boolean[] memo = new Boolean[s.length() + 1];
            return wordBreak2(s, new HashSet<>(wordDict), 0, memo);
        }

        boolean wordBreak2(String s, Set<String> wordDict, int k, Boolean[] memo) {
            int n = s.length();
            if (k == n) return true;

            if (memo[k] != null) return memo[k];

            for (int i=k + 1; i<=n; i++) {
                String word = s.substring(k, i);
                if (wordDict.contains(word) && wordBreak2(s, wordDict, i, memo)) {
                    return memo[k] = true;
                }
            }

            return memo[k] = false;
        }

        boolean recWay1(String s, List<String> wordDict) {
            Boolean[] memo = new Boolean[s.length() + 1];
            return wordBreak(s, wordDict, 0, memo);
        }

        boolean wordBreak(String s, List<String> wordDict, int k, Boolean[] memo) {
            if (k == s.length()) {
                return true;
            }

            if (memo[k] != null) {
                return memo[k];
            }

            for (int i=0; i<wordDict.size(); i++) {
                String word = wordDict.get(i);
                if (s.startsWith(word, k)) {
                    if(wordBreak(s, wordDict, k + word.length(), memo)) return memo[k] = true;
                }
            }

            return memo[k] = false;
        }
    }
}
