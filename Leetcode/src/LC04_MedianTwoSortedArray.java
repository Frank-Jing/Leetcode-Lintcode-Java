package Leetcode.src;

import java.sql.Time;

public class LC04_MedianTwoSortedArray {
    class Solution {
        /*binary search
        * Now try to find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition around which lies the median.
        *
        * Time complexity is O(log(min(x, y))
        * Space complexity is O(1)
        * https://leetcode.com/problems/median-of-two-sorted-arrays/
        * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/4
        */

        public static double findMedianSortedArrays(int[] input1, int[] input2) {
            //if input1 length is greater than switch them so that input1 is smaller than input2.
            if (input1.length > input2.length) {
                return findMedianSortedArrays(input2, input1);
            }
            int x = input1.length;
            int y = input2.length;

            int low = 0;
            int high = x;
            while (low <= high) {
                int partitionX = (low + high)/2;
                int partitionY = (x + y + 1)/2 - partitionX;

                //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
                //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
                int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
                int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

                int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
                int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

                if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                    //We have partitioned array at correct place
                    // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                    // or get max of left for odd length combined array size.
                    if ((x + y) % 2 == 0) {
                        return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                    } else {
                        return (double)Math.max(maxLeftX, maxLeftY);
                    }
                } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                    high = partitionX - 1;
                } else { //we are too far on left side for partitionX. Go on right side.
                    low = partitionX + 1;
                }
            }

            //Only we can come here is if input arrays were not sorted. Throw in that scenario.
            throw new IllegalArgumentException();
        }

        //merge sort but not fastest
        public static double mergedArrayMedian(int[] nums1, int[] nums2){
            int[] mergedList = new int[nums1.length + nums2.length];
            int i=0,j=0,k=0;
            while(i<nums1.length && j<nums2.length){
                mergedList[k++] = nums1[i] < nums2[j]? nums1[i++] : nums2[j++];
            }
            while(i<nums1.length){
                mergedList[k++] = nums1[i++];
            }
            while(j<nums2.length){
                mergedList[k++] = nums2[j++];
            }

            double ans;
            if(((nums1.length + nums2.length)&1) == 0){
                return (mergedList[(k-1)/2]+ mergedList[k/2])/2.0;
            }else{
                return + mergedList[k/2];
            }

        }

        public static void main(String[] args) {
            int[] x = {1, 3, 8, 9, 15};
            int[] y = {7, 11, 19, 21, 18, 25};
            findMedianSortedArrays(x, y);
        }
    }
}
