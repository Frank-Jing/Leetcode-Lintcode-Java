package Leetcode.src.MathBitOther;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC20_ValidParentheses {
    public boolean isValid(String s) {

        Map<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '{' || c == '(' || c == '['){
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;
                char o = stack.pop();
                if(pairs.get(o) != c) return false;
            }
        }

        return stack.isEmpty();
    }
}
