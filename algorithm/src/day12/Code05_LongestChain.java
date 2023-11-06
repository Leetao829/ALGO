package day12;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/maximum-length-of-pair-chain/
 */
public class Code05_LongestChain {
    public int findLongestChain(int[][] pairs) {
        // 先根据第一位的大小进行排序
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int n = pairs.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }
        return dp[n - 1];

    }

}
