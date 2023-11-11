package day15;

import java.util.Arrays;

/**
 * 去除重叠子区间个数最小值
 * 动态规划（区间dp）：以i为结尾的最长无重叠子区间个数
 * https://leetcode.cn/problems/non-overlapping-intervals/
 */
public class Code06_EraseOverLap {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int[] dp = new int[n];
        int res = 0;
        for(int i = 1;i < n;i++){
            dp[i] = 1;
            for(int j = 0;j < i;j++){
                if(intervals[i][0] >= intervals[j][1]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return n - res;

    }
    
}
