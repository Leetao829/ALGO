package day24;

import java.util.Arrays;

/*
 * 切棍子的最小成本：区间dp，枚举第一次切棍子的点
 * https://leetcode.cn/problems/minimum-cost-to-cut-a-stick/
 */
public class Code01_MinCostToCutStick {
    public int minCost(int n, int[] cuts) {
        //先对数组进行排序，方便计算后面地代价
        Arrays.sort(cuts);
        //对数组进行预处理，计算出切割点的代价，这一步的处理很关键，是处理dp的衡量标准
        int len = cuts.length;
        int[] costs = new int[len+2];
        costs[len + 1] = n;
        for(int i = 1;i <= len;i++) costs[i] = cuts[i-1];
        int[][] dp = new int[len+2][len+2];
        for(int i = 1;i <= len;i++) dp[i][i] = costs[i+1] - costs[i-1];
        for(int k = 2;k <= len;k++){
            for(int i = 1,j,val;i <= len - k + 1;i++){
                j = i + k - 1;
                val = costs[j+1] - costs[i-1];
                dp[i][j] = Integer.MAX_VALUE;
                for(int m = i,l,r;m <= j;m++){
                    if(m == i) dp[i][j] = Math.min(dp[i][j], val + dp[i+1][j]);
                    else if(m == j) dp[i][j] = Math.min(dp[i][j], val + dp[i][j-1] );
                    else dp[i][j] = Math.min(dp[i][j], val + dp[i][m-1] + dp[m+1][j]);
                }
            }
        }
        return dp[1][len];
    }


}
