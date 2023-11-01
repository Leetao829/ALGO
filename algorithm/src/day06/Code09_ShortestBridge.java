package day06;

import java.util.Arrays;

public class Code09_ShortestBridge {
    //有点意思，感觉可以先使用dfs再使用bfs进行解决
    public static int MAXN = 100;
    public static int[][] queue = new int[MAXN*MAXN][2];
    public static boolean[][] vis = new boolean[MAXN][MAXN];
    public static int l,r;
    public static int[] d = {-1,0,1,0,-1};
    public static int n,m;
    public static int res;
    public int shortestBridge(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        l = r = 0;
        res = 0;
        for(int i = 0; i< n;i++){
            Arrays.fill(vis[i], 0,m,false);
        }
        boolean flag = false;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(grid[i][j] == 1){
                    dfs(grid, i, j);
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        while (l < r) {
            res++;
            int size = r - l;
            for(int i = 0,x,y;i < size;i++){
                x = queue[l][0];
                y = queue[l++][1];
                for(int k = 0,nx,ny;k < 4;k++){
                    nx = x + d[k];
                    ny = y + d[k+1];
                    if(nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny]){
                        if(grid[nx][ny] == 1) return res;
                        vis[nx][ny] = true;
                        queue[r][0] = nx;
                        queue[r++][1] = ny;
                    }
                }
            }
            
        }
        return res;
    }
    public static void dfs(int[][] grid,int x,int y){
        if(x < 0 || x >= n || y < 0 || y >= m || vis[x][y] || grid[x][y] == 0) return;
        vis[x][y] = true;
        queue[r][0] = x;
        queue[r++][1] = y;
        for(int i = 0,nx,ny;i < 4;i++){
            nx = x + d[i];
            ny = y + d[i+1];
            dfs(grid, nx, ny);
        }

    }
}
