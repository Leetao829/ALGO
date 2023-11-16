package day20;
/**
 * 乘积最大子数组问题
 * https://leetcode.cn/problems/maximum-product-subarray/
 * 中间维护乘积最大值、最小值即可
 */
public class Code04_MaxMulitple {
    public int maxProduct(int[] nums){
        int n = nums.length;
        int[] maxDp = new int[n];
        int[] minDp = new int[n];
        int res = nums[0];
        maxDp[0] = minDp[0] = nums[0];
        for(int i = 1;i < n;i++){
            maxDp[i] = Math.max(nums[i], Math.max(nums[i] * maxDp[i-1], nums[i] * minDp[i-1]));
            minDp[i] = Math.min(nums[i], Math.min(nums[i] * minDp[i-1], nums[i] * maxDp[i-1]));
            res = Math.max(res, maxDp[i]);
        }
        return res;
    }
}
