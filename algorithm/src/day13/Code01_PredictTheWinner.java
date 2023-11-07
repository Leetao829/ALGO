package day13;

import java.util.Arrays;

/**
 * 预测赢家
 * https://leetcode.cn/problems/predict-the-winner/description/
 */
public class Code01_PredictTheWinner {
    public boolean predictTheWinner(int[] nums) {
        int[][] rec = new int[nums.length][nums.length];
        for(int i = 0;i < nums.length;i++) Arrays.fill(rec[i], -1);
        return dfs2(nums,rec, 0, nums.length-1) >= 0;
    }
    //方法一使用递归，在递归过程中记录最大的累加和，其中先手为正，后手为负
    /**
     * 递归含义：当前玩家在当前选择时赢过对手的最大值，相当与净胜值
     * 这个递归含义真不错，相当与统一了含义，不区分先手与后手带来的编码难度
     * @param nums
     * @param i
     * @param j
     * @return
     */
    public static int dfs(int[] nums,int i,int j){
        if(i == j) return nums[i];
        int l = nums[i] - dfs(nums, i+1, j);
        int r = nums[j] - dfs(nums, i, j-1);

        return Math.max(l,r);
    }

    //在递归过程中存在重复计算，因此考虑使用记忆化搜索
    public static int dfs2(int[] nums,int[][] rec,int i,int j){
        if(i == j) return nums[i];
        if(rec[i][j] != -1) return rec[i][j];
        int l = nums[i] - dfs2(nums, rec,i+1, j);
        int r = nums[j] - dfs2(nums, rec,i, j-1);
        rec[i][j] = Math.max(l, r);
        return rec[i][j];

    }
}
