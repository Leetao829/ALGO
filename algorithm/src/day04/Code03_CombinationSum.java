package day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//根据题意，找出数组中组合数为target的所有组合，并且不能重复
public class Code03_CombinationSum {
    //使用数组记录沿途遍历数组经过的数字
    public static int[] sum = new int[105];
    public static int index;
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
        //为防止不重复，先对数组进行排序
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(ans, candidates, target, 0, 0);
        return ans;
    }
    /**
     * 
     * @param candidates 候选数组
     * @param target 目标和
     * @param index sum数组的下标
     * @param i candidates当前来到位置的下标
     */
    public static void dfs(List<List<Integer>> ans,int[] candidates,int target,int index,int i){
        if(target == 0){
            List<Integer> list = new ArrayList<>();
            for(int k = 0;k < index;k++) list.add(sum[k]);
            ans.add(list);
            return;
        }
        if(target < 0) return;
        if(i >= candidates.length) return;
        Set<Integer> set = new HashSet<>();
        for(int j = i;j < candidates.length;j++){
            if(!set.contains(candidates[j])){
                set.add(candidates[j]);
                sum[index++] = candidates[j];
                dfs(ans,candidates, target-candidates[j], index, j+1);
                index--;
            }
        }
    }
}
