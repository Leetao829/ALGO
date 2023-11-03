package day09;
//https://leetcode.cn/problems/making-a-large-island/description/
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code02_LargestIsland {
    //大体思路其实并不难，就是统计每个岛屿的面积以及每个点对应的岛屿，
    //最后当我们加入一个节点时，我们只需要观察该节点上下左右对应的四个节点对应的岛屿加上他们的面积即可
    public static int area;//用于统计面积
    public static int count;//统计节点对应的岛屿编号
    public static int[] d = {-1,0,1,0,-1};
    public static int n;
    public static boolean[][] vis = new boolean[501][501];
    public static Map<Integer,Integer> map = new HashMap<>();
    public static Map<Integer,Integer> amap = new HashMap<>();
    public int largestIsland(int[][] grid) {
        n = grid.length;
        count = 0;
        for(int i = 0;i < n;i++) Arrays.fill(vis[i], 0,n,false);
        map.clear();
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(grid[i][j] == 1 && !vis[i][j]) {
                    area = 0;
                    count++;
                    dfs(grid, i, j);
                    amap.put(count, area);
                }
            }
        }
        int ans = 0;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                if(grid[i][j] == 0){
                    ans = Math.max(ans, mergeArea(grid, i, j));
                }
            }
        }
        return ans == 0 ? area : ans;
    }

    //0改为1之后计算岛屿的面积
    public static int mergeArea(int[][] grid,int x,int y){
        int res = 1;
        Set<Integer> set = new HashSet<>();
        for(int k = 0,nx,ny;k < 4;k++){
            nx = x + d[k];
            ny = y + d[k+1];
            if(nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1){
                int count = map.get(get(nx, ny));
                if(!set.contains(count)){
                    res += amap.get(count);
                    set.add(count);
                }
            }
        }
        return res;
    }

    //计算面积的dfs，bfs都行，这里使用dfs
    public static void dfs(int[][] grid,int x,int y){
        if(x < 0 || x >= n || y < 0 || y >= n || vis[x][y] || grid[x][y] == 0) return;
        area++;
        vis[x][y] = true;
        map.put(get(x, y), count);
        for(int k = 0,nx,ny;k < 4;k++){
            nx = x + d[k];
            ny = y + d[k+1];
            dfs(grid, nx, ny);
        }

    }

    public static int get(int x,int y){
        return x * n + y;
    }
}
