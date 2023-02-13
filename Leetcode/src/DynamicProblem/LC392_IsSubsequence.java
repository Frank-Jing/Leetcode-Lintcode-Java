package Leetcode.src.DynamicProblem;

public class LC392_IsSubsequence {

    public boolean isSubsequence_2Pointer(String s, String t) {
        if(s == null || s.toCharArray().length == 0){
            return true;
        }
        if(t == null || t.toCharArray().length == 0){
            return false;
        }

        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        int N = sChar.length;
        int M = tChar.length;

        int curr = 0;
        for(int i = 0; i < M; i++){
            if(sChar[curr] == tChar[i] && curr<N){
                curr += 1;
            }
            if(curr == N){
                return true;
            }
        }

        return false;
    }

    public boolean isSubsequence_2Pointer_2(String s, String t) {
        if(s == null || s.length() == 0){
            return true;
        }
        if(t == null || t.length() == 0){
            return false;
        }

        int curr = 0;
        int i = 0;
        while(curr < s.length() &&  i < t.length()){
            if(s.charAt(curr) == t.charAt(i) && curr < s.length()){
                curr++;
            }
            i++;
        }

        return curr == s.length();
    }
}
