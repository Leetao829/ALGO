package day12;
/**
 * 寻找最长递增子序列的个数，就是直接动态规划，有时候感觉子序列要比子数组要更好处理
 * 很明显，这一题想简单里，还是以i下标为结尾的最长递增子序列能够解决问题
 * https://leetcode.cn/problems/number-of-longest-increasing-subsequence/
 */
public class Code03_FindNumsOfLIS {
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        int maxLen = 0,ans = 0;
        for(int i = 0;i < n;i++){
            dp[i] = 1;
            cnt[i] = 1;
            for(int j = 0;j < i;j++){
                if(nums[i] > nums[j]){
                    if(1 + dp[j] > dp[i]){
                        dp[i] = 1 + dp[j];
                        cnt[i] = cnt[j];
                    }else{
                        cnt[i] += cnt[j];
                    }
                }
            }
            if(dp[i] > maxLen){
                maxLen = dp[i];
                ans = cnt[i];
            }else if(dp[i] == maxLen){
                ans += cnt[i];
            }

        }
        return ans;
        
    }
}
