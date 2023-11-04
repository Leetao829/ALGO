package day10;
/**
 * 飞地数量
 * https://leetcode.cn/problems/number-of-enclaves/
 * 题目不难，先从边界进行深搜，再全局进行深搜
 */
public class Code02_EnCalvesNum {
    public static int n,m;
    public static int[] d = {-1,0,1,0,-1};
    public static int lands;//用于统计陆地数量
    public static int ans;

    public int numEnclaves(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        ans = 0;
        for(int i = 0;i < m;i++){
            if(grid[0][i] == 1) dfs(grid, 0,i);
            if(grid[n-1][i] == 1) dfs(grid, n-1, i);
        }
        for(int i = 0;i < n;i++){
            if(grid[i][0] == 1) dfs(grid,i,0);
            if(grid[i][m-1] == 1) dfs(grid, i, m-1);
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(grid[i][j] == 1) {
                    lands = 0;
                    dfs(grid, i, j);
                    ans += lands;
                }
            }
        }
        return ans;
    }
    public static void dfs(int[][] grid,int x,int y){
        if(x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == 0) return;
        lands++;
        grid[x][y] = 0;
        for(int k = 0,nx,ny;k < 4;k++){
            nx = x + d[k];
            ny = y + d[k+1];
            dfs(grid, nx, ny);
        }
    }
    
}
