package Leetcode.src.MathBitOther;

import java.util.Arrays;

public class LC169_MajorityElement {

    class Solution_BoyerMooreVoting {
        public int majorityElement(int[] nums) {
            int count = 0;
            int candidate = nums[0];

            for(int num : nums){
                if(count == 0){
                    candidate = num;
                }
                int add = num == candidate? 1 : -1;
                count += add;
            }

            return candidate;
        }
    }

    class Solution_DividConquer {
        public int majorityElement(int[] nums) {
            return helper(nums, 0, nums.length-1);
        }

        public int helper(int[] nums, int start, int end){
            if(start == end){
                return nums[start];
            }
            int mid = start + (end - start)/2;
            int left = helper(nums, start, mid);
            int right = helper(nums, mid+1, end);

            int ans = left;
            if(left != right){
                ans = countNum(nums, left) > countNum(nums, right)? left : right;
            }

            return ans;
        }

        public int countNum(int[] nums, int target){
            int count = 0;
            for(int num : nums){
                if(num == target){
                    count++;
                }
            }
            return count;
        }
    }

    class Solution_sort {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length/2];
        }
    }
}
