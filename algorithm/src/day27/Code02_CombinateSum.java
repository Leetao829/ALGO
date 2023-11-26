package day27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和2：同样是带路径的递归过程，可以将每个数字按照取出的数量进行枚举，这样一定不会重复
 * https://leetcode.cn/problems/combination-sum-ii/
 */
public class Code02_CombinateSum {
    public static int[] sum = new int[100];
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
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
        int j = i + 1;
        while (j < candidates.length && candidates[j] == candidates[i]) {
            j++;
        }
        //[i,j)上都是相同的
        //取零个
        dfs(res, candidates, target, j, index);
        //取多个
        for(int mul = 1;mul <= j - i && mul * candidates[i] <= target;mul++){
            sum[index++] = candidates[i];
            dfs(res, candidates, target - mul * candidates[i], j, index);
        }
    }
}
