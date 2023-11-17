package day21;
/**
 * 最长递增子序列问题，前面写的时间复杂度有点高，这次使用时间复杂度底的算法，最终时间复杂度在O(nlogn)
 * 
 */
public class Code04_LengthOfLIS {
    public int lengthOfLIS(int[] nums){
        int n = nums.length;
        if(n < 2) return n;
        int[] end = new int[n];//其中end[i]表示长度为i+1的子序列当中结尾的最小值，通过这个结构我们能够直接
        //求出答案
        int r = 0;
        end[r++] = nums[0];
        for(int i = 1;i < n;i++){
            int index = search(end, 0, r-1, nums[i]);
            if(index == -1) end[r++] = nums[i];
            else end[index] = nums[i];
        }
        return r;
    }


    /**
     * 二分查找，在[l,r]中寻找第一个大于等于target的下标
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public static int search(int[] nums,int l,int r,int target){
        if(target > nums[r]) return -1;
        int res = r;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if(nums[mid] >= target) {
                res = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
            
        }
        return res;
    }
    
}
