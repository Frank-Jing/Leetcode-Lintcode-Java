package Leetcode.src.DynamicProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC22_GenerateParentheses {
    class Solution {
        Map<Integer, List<String>> cache = new HashMap<>();

        public List<String> generateParenthesis(int n) {
            if (!cache.containsKey(n)) {
                List<String> ans = new ArrayList();
                if (n == 0) {
                    ans.add("");
                    cache.put(0, ans);
                } else {
                    for (int c = 0; c < n; ++c)
                        for (String left : generateParenthesis(c))
                            for (String right : generateParenthesis(n - 1 - c))
                                ans.add("(" + left + ")" + right);
                    cache.put(n, ans);
                }
            }

            return cache.get(n);
        }
    }
}
