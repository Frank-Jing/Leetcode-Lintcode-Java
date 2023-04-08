package Leetcode.src.Graph;

/* The knows API is defined in the parent class Relation.
   boolean knows(int a, int b);
*/
public class LC277_FindCelebrity {
    public class Solution {
        public int findCelebrity(int n) {
            int[] in = new int[n];
            int[] out = new int[n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(i != j && knows(i, j)){
                        in[j]++;
                        out[i]++;
                    }
                }
            }
            int ans = -1;
            for(int i = 0; i< n; i++){
                if(in[i] == n-1 && out[i] == 0){
                    ans = i;
                }
            }
            return ans;
        }
    }

    public class Solution_BruteForce {
        public int findCelebrity(int n) {
            for(int cand = 0; cand< n; cand++){
                int other = 0;
                for(; other<n; other++){
                    if(cand == other) continue;
                    if(knows(cand, other) || !knows(other, cand)){
                        break;
                    }
                }
                if(other == n){
                    return cand;
                }
            }
            return -1;
        }
    }

    // Time complexity O(N)
    public class Solution_2 {
        public int findCelebrity(int n) {
            int cand = 0; //assume candidate is the celebratiy
            for(int other = 1; other < n; other ++){
                if(knows(cand, other) || !knows(other, cand)){
                    // candidate knows other or other knows candidate
                    // current candidate is not the celebrity
                    // select other as candidate
                    cand = other;
                }
            }

            for(int other = 0; other < n; other++){
                if(other == cand) continue;
                if(!knows(other, cand) || knows(cand, other)){
                    return -1;
                }
            }
            return cand;
        }
    }
}
