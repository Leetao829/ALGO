package day05;

import java.util.ArrayList;
import java.util.List;
/**
 * 太平洋与大西洋问题，就是使劲递归呗，没啥好说的，但是我感觉使用动态规划反而更好，维护两个二维数组就行了
 */
public class Code03_PacificAtlantic {

    public static int[] d = {-1,0,1,0,-1};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        //题目理解有问题，不能使用简单的dp能够解决问题，还是需要四个方向进行深搜
        List<List<Integer>> ans = new ArrayList<>();
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] p = new boolean[n][m];
        boolean[][] f = new boolean[n][m];
        for(int i = 0;i < n;i++){
            dfs(i,0,p,heights);
            dfs(i,m-1,f,heights);
        }
        for(int i = 0;i < m;i++){
            dfs(0,i,p,heights);
            dfs(n-1,i,f,heights);
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(p[i][j] && f[i][j]){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    
    public static void dfs(int i,int j,boolean[][] ocean,int[][] heights){
        if(ocean[i][j]) return;
        ocean[i][j] = true;
        for(int k = 0,ni,nj;k < 4;k++){
            ni = i + d[k];
            nj = j + d[k+1];
            if(ni >= 0 && ni < ocean.length && nj >= 0 && nj < ocean[0].length && heights[ni][nj] >= heights[i][j]) dfs(ni,nj,ocean,heights);
        }
    }
}
