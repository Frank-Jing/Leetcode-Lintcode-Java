package Leetcode.src.DynamicProblem;
import java.util.ArrayList;
import java.util.List;

public class LC118_PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i = 0; i<numRows; i++){
            List<Integer> row = new ArrayList<>();
            for(int j=0; j<=i; j++){
                row.add(entryGen(i, j));
            }
            res.add(row);
        }
        return res;
    }

    public int entryGen(int lo, int hi){
        if(hi == 0 || hi == lo) return 1;
        if(hi >= lo/2) hi=lo-hi;
        double up = 1, bo = 1;
        while(hi>0){
            up*= lo--;
            bo*= hi--;
        }
        int entry = (int)(up/bo);
        return entry;
    }
}
