package day10;
/**
 * 岛屿数量
 * 再熟悉一下模板
 * https://leetcode.cn/problems/number-of-islands/
 */
public class Code04_IslandNumber {
    public static int n,m;
    public static int[] d = {-1,0,1,0,-1};
    public static int ans;
    public int numIslands(char[][] grid) {
        ans = 0;
        n = grid.length;
        m = grid[0].length;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    public static void dfs(char[][] grid,int x,int y){
        if(x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == '0') return;
        grid[x][y] = '0';
        for(int k = 0,nx,ny;k < 4;k++){
            nx = x + d[k];
            ny = y + d[k+1];
            dfs(grid, nx, ny);
        }
    }
    
}
