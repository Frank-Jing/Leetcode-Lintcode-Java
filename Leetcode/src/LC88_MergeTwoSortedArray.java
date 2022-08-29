package Leetcode.src;

public class LC88_MergeTwoSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int num1cur = m-1;
        int num2cur = n-1;
        int cur = m+n-1;

        while(num1cur >= 0 && num2cur >= 0){
            if(nums1[num1cur] >= nums2[num2cur]){
                nums1[cur] = nums1[num1cur];
                num1cur--;
            }else{
                nums1[cur] = nums2[num2cur];
                num2cur--;
            }
            cur--;
        }

        if(num2cur>=0){
            while(cur>=0){
                nums1[cur] = nums2[num2cur];
                cur--;
                num2cur--;
            }
        }

    }
}
