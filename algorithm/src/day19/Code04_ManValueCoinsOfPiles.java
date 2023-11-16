package day19;

import java.util.List;

/**
 * 从一堆硬币栈中寻找指定次数的硬币最大值
 * 分组背包dp
 * https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles/
 */
public class Code04_ManValueCoinsOfPiles {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n+1][k+1];
        for(int i = 1;i <= n;i++){
            List<Integer> group = piles.get(i-1);
            int m = Math.min(group.size(), k);
            int[] preSum = new int[m+1];//构造前缀和，方便统计
            for(int h = 1;h <= m;h++){
                preSum[h] = preSum[h-1] + group.get(h-1);
            }
            for(int j = 0;j <= k;j++){
                dp[i][j] = dp[i-1][j];
                for(int h = 1;h <= Math.min(m,j);h++){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - h] + preSum[h]);
                }
            }
        }
        return dp[n][k];
    }
}
