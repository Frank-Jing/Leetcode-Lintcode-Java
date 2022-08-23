package Leetcode.src;

public class LC04_MedianTwoSortedArray {
    class Solution {
        //binary search
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n1 = nums1.length;
            int n2 = nums2.length;
            if(n1 > n2) return findMedianSortedArrays(nums2, nums1);

            int k = (n1 + n2 + 1)/2;

//         if(n1 == 0){
//             if(n2%2 == 1) return (double) nums2[k-1];
//             else return (double)(nums2[k-1] + nums2[k])*0.5;
//         }

//         if(n1 == 1 && n2 == 1) return (double)(nums1[0] + nums2[0])*0.5;

//         if(nums1[n1-1] < nums2[0]){
//             if(n1 == n2) return (double)(nums1[n1-1] + nums2[0])*0.5;
//             if((n1+n2)%2 == 1){
//                 return (double)nums2[k-n1-1];
//             }else{
//                 return (double)((nums2[k-n1-1] + nums2[k-n1])*0.5);
//             }
//         }

//         if(nums1[0] > nums2[n2-1]){
//             if(n1 == n2) return (double)(nums2[n2-1] + nums1[0])*0.5;
//             if((n1+n2)%2 == 1){
//                 return (double)nums2[n2 - (k-n1)];
//             }else{
//                 return (double)((nums2[n2 - (k-n1) - 1] + nums2[n2 - (k-n1)])*0.5);
//             }
//         }


            int left=0, right=n1;
            while(left < right){
                int mid = (left+right)/2;
                if(nums1[mid] < nums2[k-mid-1]){
                    left = mid + 1;
                }else{
                    right = mid;
                }
            }

            double res = (double)Math.max(left == 0? Integer.MIN_VALUE: nums1[left-1],
                    k-left ==0? Integer.MIN_VALUE: nums2[k-left-1]);
            if((n1+n2)%2 == 0){
                int next = Math.min(left == n1? Integer.MAX_VALUE: nums1[left],
                        k-left == n2? Integer.MAX_VALUE: nums2[k-left]);
                res = (double) (res+next)/2;
            }
            return res;
        }

        //merge sort but not fastest
        public double mergedArrayMedian(int[] nums1, int[] nums2){
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
    }
}
