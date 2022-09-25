package Leetcode.src.Sorting;

public class LC912_SortArray {
    class MergeSort {
        public int[] sortArray(int[] nums) {
            if(nums.length < 2) return nums;

            return process(nums, 0, nums.length - 1);
        }

        public int[] process(int[] nums, int start, int end){
            if(start == end){
                return new int[]{nums[start]};
            }

            int mid = start + ((end - start) >> 1);
            int[] left = process(nums, start, mid);
            int[] right = process(nums, mid + 1, end);

            return merge(left, right);
        }

        public int[] merge(int[] left, int[] right){
            int len1 = left.length;
            int len2 = right.length;
            int[] merged = new int[len1 + len2];

            int i = 0, j = 0, k = 0;
            while(i < len1 && j < len2){
                merged[k++] = left[i] <= right[j]? left[i++] : right[j++];
            }

            while(i < len1){
                merged[k++] = left[i++];
            }
            while(j < len2){
                merged[k++] = right[j++];
            }

            return merged;

        }
    }

    class QuickSort {
        public int[] sortArray(int[] nums) {
            if(nums.length < 2) return nums;

            process(nums, 0, nums.length - 1);

            return nums;
        }

        public void process(int[] nums, int L, int R){
            if(L >= R) return;

            swap(nums, L + (int)(Math.random() * (R-L+1)), R);
            int[] equalArea = partition(nums, L, R);
            process(nums, L, equalArea[0] - 1);
            process(nums, equalArea[1]+1, R);
        }

        public void swap(int[] nums, int ind1, int ind2){
            int temp = nums[ind1];
            nums[ind1] = nums[ind2];
            nums[ind2] = temp;
        }

        public int[] partition(int[] nums, int L, int R){
            if(L>R){
                return new int[]{-1, -1};
            }
            if(L == R){
                return new int[]{L, R};
            }

            int ptr = L;
            int leftBnd = L-1, rightBnd = R;
            while(ptr < rightBnd){
                if(nums[ptr] == nums[R]){
                    ptr++;
                }else if(nums[ptr] < nums[R]){
                    swap(nums, ptr++, ++leftBnd);
                }else{
                    swap(nums, ptr, --rightBnd);
                }
            }
            swap(nums, R, rightBnd);

            return new int[]{leftBnd+1, rightBnd};
        }

    }
}
