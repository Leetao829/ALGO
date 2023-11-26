package day27;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和1，典型的递归，没事再写写
 * 典型的带路径递归过程，中间使用数组进行记录
 * https://leetcode.cn/problems/combination-sum/
 */
public class Code01_CombinateSum1 {
    public static int[] sum = new int[20];//根据题目数据量估计大小
    
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, candidates, target, 0, 0);
        return res;
    }

    public static void dfs(List<List<Integer>> res,int[] candidates,int target,int i,int index){
        if(target == 0){
            List<Integer> list = new ArrayList<>();
            for(int k = 0;k < index;k++) list.add(sum[k]);
            res.add(list);
            return;
        }
        if(i == candidates.length) return;
        //sum中添加零个元素
        dfs(res, candidates, target, i+1, index);
        //添加多个元素
        for(int mul = 1;mul * candidates[i] <= target;mul++){
            sum[index++] = candidates[i];
            dfs(res, candidates, target - mul * candidates[i], i + 1, index);
        }
    }
}
