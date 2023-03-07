package Leetcode.src.Sorting;

public class LC4_MedianOfTwoSortedArray {
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n1 = nums1.length;
            int n2 = nums2.length;
            if(n1 > n2) return findMedianSortedArrays(nums2, nums1);

            int k = (n1 + n2 + 1)/2;
            int start = 0, end = n1;
            double res = 0;
            while(start <= end){
                int cutAtNum1 = (start + end) >>> 1;
                int cutAtNum2 = k - cutAtNum1;

                int L1 = cutAtNum1 == 0? Integer.MIN_VALUE : nums1[cutAtNum1 - 1];
                int R1 = cutAtNum1 == n1? Integer.MAX_VALUE : nums1[cutAtNum1];
                int L2 = cutAtNum2 == 0? Integer.MIN_VALUE : nums2[cutAtNum2 - 1];
                int R2 = cutAtNum2 == n2? Integer.MAX_VALUE : nums2[cutAtNum2];


                if(L1 <= R2 && L2 <= R1){
                    if( (n1 + n2)%2 == 0){
                        res = (Math.max(L1, L2) + Math.min(R1, R2))/2.0;
                    }else{
                        res = (double) Math.max(L1, L2);
                    }
                    break;
                }else if(L1 > R2){
                    end = cutAtNum1 - 1;
                }else if(L2 > R1){
                    start = cutAtNum1 + 1;
                }
            }

            return res;
        }
    }
}
