package day11;

import java.util.Arrays;

/**
 * 青蛙过河
 * https://leetcode.cn/problems/frog-jump/description/
 * 很好的一道题，使用两个状态：{当前位置，上一次跳跃的距离}优化掉了暴力搜索（带路径的递归）
 * 当问题转化成为只取决于这两个状态之后（无后效性），我们就可以想办法使用记忆化搜索或者dp了
 */
public class Code03_FragCrossRiver {

    public boolean canCross(int[] stones) {
        int n = stones.length;
        Boolean[][] rec = new Boolean[n][n];
        Arrays.fill(rec[n-1], true);
        return dfs(stones, rec, 0, 0);

    }
    public static boolean dfs(int[] stones, Boolean[][] rec,int i,int preDis){
        if(i == stones.length-1){
            return true;
        }
        if(rec[i][preDis] != null) return rec[i][preDis];
        for(int curDis = preDis-1;curDis <= preDis + 1;curDis++){
            int j = Arrays.binarySearch(stones, i+1,stones.length,stones[i]+curDis);
            if(j >= 0 && dfs(stones, rec, j, curDis)){
                rec[i][preDis] = true;
                return true;
            }
        }
        rec[i][preDis] = false;
        return false;
    }

}
