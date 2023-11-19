package day23;

import java.util.Arrays;

/**
 * 预测赢家
 * https://leetcode.cn/problems/predict-the-winner/
 */
public class Code03_PredictTheWinner {
    public boolean predictTheWinner(int[] nums){
        int n = nums.length;
        int[][] rec = new int[n][n];//记忆化搜索的缓存表
        int sum = 0;
        for(int i = 0;i < n;i++) {
            Arrays.fill(rec[i], -1);
            sum += nums[i];
        }
        int f = dfs(nums, 0, n-1, rec);

        return f >= sum - f;
    }

    /*
     * 表示先手能够取得的最大值，这里甲先手，使用记忆化搜索
     */
    public static int dfs(int[] nums,int l,int r,int[][] rec){
        if(l > r) return 0;
        if(rec[l][r] != -1) return rec[l][r];
        if(l == r){
            rec[l][r] = nums[l];
            return nums[l];
        }
        if(r - l == 1){
            rec[l][r] = Math.max(nums[l], nums[r]);
            return rec[l][r];
        }
        int p1 = nums[l] + Math.min(dfs(nums, l + 2, r, rec), dfs(nums, l + 1, r - 1, rec));
        int p2 = nums[r] + Math.min(dfs(nums, l + 1, r - 1, rec), dfs(nums, l, r - 2, rec));
        rec[l][r] = Math.max(p1, p2);
        return rec[l][r];
    }


    
}
