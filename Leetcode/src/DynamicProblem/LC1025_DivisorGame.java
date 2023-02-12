package Leetcode.src.DynamicProblem;

//https://leetcode.com/problems/divisor-game
public class LC1025_DivisorGame {
    public boolean divisorGame(int n) {
        return process(n);
    }

    public boolean process(int n){
        // if(n == 2){
        //     return true;
        // };
        // if(n == 3){
        //     return false;
        // };

        boolean res = false;
        for(int x = 1; x < n; x++){
            if(n%x == 0){
                if (process(n-x) == false){
                    res = true;
                    break;
                }
            }
        }
        return res;
    }


    public boolean divisorGame2(int n) {
        boolean[] dp = new boolean[n+1];
        dp[0] = false;
        dp[1] = false;
        // dp[2] = true;
        // dp[3] = false;
        if(n > 1){
            for(int i = 2; i<= n; i++){
                for(int x = 1; i-x>0; x++){
                    if(i%x == 0){
                        if(dp[i-x] == false){
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[n];
    }

    public boolean divisorGame3(int n) {
        return n%2 == 0;
    }
}
