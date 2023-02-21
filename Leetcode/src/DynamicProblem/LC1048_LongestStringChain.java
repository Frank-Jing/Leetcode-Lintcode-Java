package Leetcode.src.DynamicProblem;

import java.util.*;

public class LC1048_LongestStringChain {
    class Solution_memo {
        public int longestStrChain(String[] words) {
            Map<String, Integer> dp = new HashMap<>();
            Arrays.sort(words, (a, b) -> a.length() - b.length());

            int maxChain = 1;
            for (String word : words) {
                int len = 1;
                for (int i = 0; i < word.length(); i++) {
                    String pred = word.substring(0, i) + word.substring(i + 1, word.length());
                    len = Math.max(len, dp.getOrDefault(pred, 0) + 1);
                }
                dp.put(word, len);
                maxChain = Math.max(maxChain, len);
            }

            return maxChain;
        }
    }


    class Solution_recursion_momo {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> wordSet = new HashSet<>(); // Important to have.
        // the process will rely on set. Or the recursion/dfs process would save each character

        public int longestStrChain(String[] words) {

            Collections.addAll(wordSet, words);

            int maxChain = 1;
            for (String word : words) {
                int len = process(words, word);
                maxChain = Math.max(maxChain, len);
            }

            return maxChain;
        }

        public int process(String[] words, String word) {
            if (!wordSet.contains(word)) return 0;

            if (memo.containsKey(word)) {
                return memo.get(word);
            }

            int len = 1;
            for (int i = 0; i < word.length(); i++) {
                String pred = word.substring(0, i) + word.substring(i + 1, word.length());
                len = Math.max(len, process(words, pred) + 1);
            }
            memo.put(word, len);

            return len;
        }
    }
}
