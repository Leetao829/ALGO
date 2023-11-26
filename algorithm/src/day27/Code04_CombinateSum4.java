package day27;

/**
 * 组合总和：这一题只用求解个数，不涉及到带路径的递归过程，可以考虑使用动态规划
 * 这一题是完全背包问题
 * https://leetcode.cn/problems/combination-sum-iv/
 * 
 */
public class Code04_CombinateSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0)
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}
