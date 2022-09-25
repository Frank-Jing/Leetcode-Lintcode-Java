package Leetcode.src.Sorting;

import java.util.Arrays;

public class LC215_KthElementInArray {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            if(nums.length < 2) return nums[0];

            k = k -1;
            // swap(nums, (int)(Math.random() * nums.length), 0);
            int[] equalArea = netherlandFlag(nums, 0, nums.length - 1); //decending order
            if(k >= equalArea[0] && k<= equalArea[1]){
                return nums[k];
            }else if(k < equalArea[0]){
                return findKthLargest(Arrays.copyOfRange(nums, 0, equalArea[0]), k+1);
            }else if(k> equalArea[1]){
                return findKthLargest(Arrays.copyOfRange(nums, equalArea[1]+1, nums.length), k - equalArea[1]);
            }

            return -1;
        }

        public void swap(int[] nums, int ind1, int ind2){
            int temp = nums[ind1];
            nums[ind1] = nums[ind2];
            nums[ind2] = temp;
        }

        public int[] netherlandFlag(int[] nums, int L, int R){
            if(L>R){
                return new int[]{-1, -1};
            }
            if(L == R){
                return new int[]{L, R};
            }

            int ptr = L;
            int leftBnd = L, rightBnd = R+1;
            while(ptr < rightBnd){
                if(nums[ptr] == nums[L]){
                    ptr++;
                }else if(nums[ptr] > nums[L]){
                    swap(nums, ptr++, ++leftBnd);
                }else{
                    swap(nums, ptr, --rightBnd);
                }
            }
            swap(nums, L, leftBnd);

            return new int[]{leftBnd, rightBnd-1};
        }
    }
}
