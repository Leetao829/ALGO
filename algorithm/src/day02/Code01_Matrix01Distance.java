package day02;

import java.util.Arrays;

public class Code01_Matrix01Distance {
    //求解每一个格子到最近0的距离，这其实很明显是一个bfs的问题
    public static int MAXN = 10001;
    public static int[][] queue = new int[MAXN][2];
    public static boolean[][] visited = new boolean[MAXN][MAXN];
    public static int[] d = {-1,0,1,0,-1};
    public static int l,r;

    public int[][] updateMatrix(int[][] mat) {
        l = r = 0;
        int n = mat.length;
        int m = mat[0].length;
        for(int i = 0;i < n;i++){
            Arrays.fill(visited[i], 0,m,false);
        }
        int[][] res = new int[n][m];
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(mat[i][j] == 0){
                    queue[r][0] = i;
                    queue[r++][1] = j;
                    visited[i][j] = true;
                }
            }
        }
        int dis  = -1;
        while (l < r) {
            dis++;
            int size = r - l;
            for(int i = 0;i < size;i++){
                int cx = queue[l][0];
                int cy = queue[l++][1];
                res[cx][cy] = dis;
                for(int k = 0,nx,ny;k < 4;k++){
                    nx = cx + d[k];
                    ny = cy + d[k+1];
                    if(nx < 0 || nx > n-1 || ny < 0 || ny > m-1 || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    queue[r][0] = nx;
                    queue[r++][1] = ny;
                }

            }
            
        }

        return res;
    }


}
