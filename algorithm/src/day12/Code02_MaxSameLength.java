package day12;
/**
 * 最长重复子数组长度，十分明显的一道动态规划的题目，看看自己能不能规划出来
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 */
public class Code02_MaxSameLength {
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int res = 0;
        int[][] dp = new int[n+1][m+1];
        for(int i = 1;i <= n;i++){
            for(int j = 0;j <= m;j++){
                if(nums1[i-1] == nums2[j-1]){
                    //数字相等
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                res = Math.max(res, dp[i][j]);
            }
        }

        return res;
    }
    
}
