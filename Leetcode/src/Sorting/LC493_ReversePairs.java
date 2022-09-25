package Leetcode.src.Sorting;

public class LC493_ReversePairs {
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        return process(nums, 0, nums.length - 1);
    }

    public int process(int[] nums, int L, int R){
        if(L>=R) return 0;

        int mid = L + ((R-L)>>1);
        return process(nums, L, mid) + process(nums, mid+1, R) + merge(nums,L,mid, R);
    }

    public int merge(int[] nums, int L, int M, int R){
        int ptr = M + 1;
        int ans = 0;
        int cur = L;
        while(cur <= M){
            while(ptr <= R && (long) nums[cur] > (long)nums[ptr] * 2){
                ptr++;
            }
            ans += ptr - (M+1);
            cur++;
        }

        int[] helper = new int[R-L+1];
        int i = 0, p1 = L, p2 = M+1;
        while(p1 <= M && p2 <= R){
            helper[i++] = nums[p1] < nums[p2]? nums[p1++] : nums[p2++];
        }

        while(p1 <= M){
            helper[i++] = nums[p1++];
        }
        while(p2 <= R){
            helper[i++] = nums[p2++];
        }

        for(i=0; i< helper.length; i++){
            nums[L+i] = helper[i];
        }

        return ans;
    }
}
