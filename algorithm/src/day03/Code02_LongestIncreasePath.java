package day03;

public class Code02_LongestIncreasePath {
    public static int[] d = {-1,0,1,0,-1};
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int res = 1;
        int[][] memo =new int[n][m];
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                res = Math.max(res, dfs(matrix, memo, i, j));
            }
        }
        return res;
    }
    public static int dfs(int[][] matrix,int[][] memo,int i,int j){
        if(memo[i][j] != 0) return memo[i][j];
        memo[i][j]++;
        for(int k = 0,ni,nj;k < 4;k++){
            ni = i + d[k];
            nj = j + d[k+1];
            if(ni >= 0 && ni < matrix.length && nj >= 0 && nj < matrix[0].length && matrix[i][j] < matrix[ni][nj]){
                memo[i][j] = Math.max(memo[i][j], 1+dfs(matrix, memo, ni, nj));
            }
        }
        return memo[i][j];
    }
    
}
