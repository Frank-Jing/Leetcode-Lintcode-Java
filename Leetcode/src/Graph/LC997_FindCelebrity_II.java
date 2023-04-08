package Leetcode.src.Graph;

public class LC997_FindCelebrity_II {
    class Solution {
        public int findJudge(int n, int[][] trust) {
            if(trust.length < n-1) return -1; // not all people trust judge

            int[] in = new int[n+1];
            int[] out = new int[n+1];

            for(int[] relation : trust){
                in[relation[1]]++;
                out[relation[0]]++;
            }

            int judge = -1;
            for(int i = 1; i <= n; i++){
                if(in[i] == n-1 && out[i] == 0){
                    judge = i;
                    break;
                }
            }
            return judge;
        }
    }
}
