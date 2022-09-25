package Leetcode.src.MathBitOther;

public class LC1461_CheckStringContainAllBinaryCodes {
    public boolean hasAllCodes(String s, int k) {
        int max = (1<<k) - 1;
        int cnt = max + 1;
        boolean[] seen = new boolean[cnt];
        int hashVal = 0;

        for(int i = 0; i< s.length(); i++){
            hashVal = ((hashVal << 1) & max) | (s.charAt(i) - '0');
            if(i >= k-1 && !seen[hashVal]){
                seen[hashVal] = true;
                cnt--;
            }
            if(cnt == 0){
                return true;
            }
        }

        return false;
    }
}
