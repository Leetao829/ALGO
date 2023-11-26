package day27;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合综合，还是带路径的递归
 * https://leetcode.cn/problems/combination-sum-iii/
 */
public class Code03_CombinateSum {
    public static int[] sum = new int[10];
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k, n, 0, res, 1);
        return res;

    }
    public static void dfs(int k,int n,int index,List<List<Integer>> res,int i){
        if(k == 0){
            if(n == 0){
                List<Integer> list = new ArrayList<>();
                for(int j = 0;j < index;j++){
                    list.add(sum[j]);
                }
                res.add(list);
            }
            return;
        }
        if(i == 10) return;
        //不取
        dfs(k, n, index, res, i + 1);
        //取
        sum[index++] = i;
        dfs(k - 1, n - i, index, res, i + 1);
    }
    
}
