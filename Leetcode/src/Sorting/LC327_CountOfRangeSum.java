package Leetcode.src.Sorting;

public class LC327_CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0) return 0;

        //get preSum
        long[] preSum = new long[nums.length];
        preSum[0] = nums[0];
        for(int i=1; i < nums.length; i++){
            preSum[i] = preSum[i-1] + nums[i];
        }

        return process(preSum, 0, preSum.length-1, lower, upper);
    }

    public int process(long[] preSum, int l, int r, int lower, int upper){
        if(l == r){
            //base case that preSum[0,l] is within the range
            return preSum[l] >= lower && preSum[l] <= upper? 1:0;
        }
        int mid = l + ((r-l)>>1);
        return process(preSum, l, mid, lower, upper) +
                process(preSum, mid+1, r, lower, upper)+
                mergeSort(preSum, l, mid, r, lower, upper);
    }

    public int mergeSort(long[] preSum, int l, int m, int r, int lower, int upper){
        int ans = 0;
        int ptrL = l, ptrR = l;
        int cur = m+1;
        while(cur <= r){
            long min = preSum[cur] - upper;
            long max = preSum[cur] - lower;
            while(preSum[ptrL] < min && ptrL <= m){
                ptrL++;
            }
            while(preSum[ptrR] <= max && ptrR <= m){
                ptrR++;
            }
            ans += ptrR - ptrL;
            cur++;
        }

        long[] helper = new long[r-l+1];
        ptrL = l;
        ptrR = m+1;
        int i=0;
        while(ptrL<=m && ptrR <= r){
            helper[i++] = preSum[ptrL] <= preSum[ptrR]? preSum[ptrL++]:preSum[ptrR++];
        }
        while(ptrL <= m){
            helper[i++] = preSum[ptrL++];
        }
        while(ptrR <= r){
            helper[i++] = preSum[ptrR++];
        }
        for(i = 0; i< helper.length; i++){
            preSum[l+i] = helper[i];
        }

        return ans;
    }
}
