package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 花园种花，要求种出不相同颜色的花，先写一个深搜试一试吧
 * https://leetcode.cn/problems/add-one-row-to-tree/
 */
public class Code01_GardenNoAdj {
    public static int MAXN = 10005;
    public static boolean[][] can = new boolean[MAXN][4];//用于标记每个花园能够访问的花
    public static int n;
    public static int[] res = new int[MAXN];
    public int[] gardenNoAdj(int num, int[][] paths) {
        n = num;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0;i <= n;i++){
            graph.add(new ArrayList<>());
            Arrays.fill(can[i], true);
        } 
        for(int i = 0;i < paths.length;i++){
            graph.get(paths[i][0]).add(paths[i][1]);
            graph.get(paths[i][1]).add(paths[i][0]);
        }
        dfs(graph, 1);
        int[] ans = new int[n];

        for(int i = 0;i < n;i++) ans[i] = res[i+1];

        return ans;
    }
    public static void dfs(List<List<Integer>> graph,int i){
        if(i > n) return;
        int k;
        for(k = 0;k < 4;k++){
            if(can[i][k]) {
                res[i] = k+1;
                break;
            }
        }
        for(int g : graph.get(i)) can[g][k] = false;
        dfs(graph, i+1);

    }



}