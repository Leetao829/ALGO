package day11;
/**
 * 矩阵当中最长递增路径，是使用记忆化搜索的好题
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
 */
public class Code04_LongestIncresingPath {
    public static int[] d = {-1,0,1,0,-1};
    public static int n,m;
    public int longestIncreasingPath(int[][] matrix){
        n = matrix.length;
        m = matrix[0].length;
        int[][] rec = new int[n][m];
        int res = 1;
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                res = Math.max(res, dfs(matrix, rec, i, j));
            }
        }
        return res;
    }

    public static int dfs(int[][] matrix,int[][] rec,int x,int y){
        if(rec[x][y] != 0) return rec[x][y];
        rec[x][y]++;
        for(int k = 0,nx,ny;k < 4;k++){
            nx = x + d[k];
            ny = y + d[k+1];
            if(nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[x][y] < matrix[nx][ny]){
                rec[x][y] = Math.max(rec[x][y], 1 + dfs(matrix, rec, nx, ny));
            }
        }
        return rec[x][y];
    }
}
