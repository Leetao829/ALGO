package day10;

import java.util.Arrays;

/**
 * 为连通分量的边界进行着色
 * https://leetcode.cn/problems/coloring-a-border/description/
 * 感觉就是在遍历的过程中加上判断条件
 */
public class Code03_ColorBorder {
    public static int MAXN = 51;
    public static int n, m, oldColor, newColor;
    public static boolean[][] vis = new boolean[MAXN][MAXN];
    public static boolean[][] border = new boolean[MAXN][MAXN];// 用于记录边界点
    public static int[] d = { -1, 0, 1, 0, -1 };

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        n = grid.length;
        m = grid[0].length;
        oldColor = grid[row][col];
        newColor = color;
        for (int i = 0; i < n; i++){
            Arrays.fill(vis[i], 0, m, false);
            Arrays.fill(border[i], 0, m, false);
        }
            
        
        if (oldColor != newColor)
            dfs(grid, row, col);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (border[i][j])
                    grid[i][j] = newColor;
            }
        }
        return grid;
    }

    public static void dfs(int[][] grid, int x, int y) {
        // dfs一开始写的稀烂，还是得用临时空间记录
        if (x < 0 || x >= n || y < 0 || y >= m || vis[x][y] || grid[x][y] != oldColor)
            return;
        vis[x][y] = true;
        if (x - 1 < 0 || x + 1 >= n || y - 1 < 0 || y + 1 >= m) {
            border[x][y] = true;
        } else {
            for (int k = 0, nx, ny; k < 4; k++) {
                nx = x + d[k];
                ny = y + d[k + 1];
                if (grid[nx][ny] != oldColor) {
                    border[x][y] = true;
                    break;
                }
            }
        }
        for (int k = 0, nx, ny; k < 4; k++) {
            nx = x + d[k];
            ny = y + d[k + 1];
            dfs(grid, nx, ny);
        }
    }
}
