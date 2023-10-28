package day02;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 有时候，dj算法使用的还是很广泛的，解决问题的思路永远是最重要的，
 * 得能够分辨出来我们使用的是dj算法，这一题的思路最重要的还是有广搜的思想
 */
public class Code03_PathWithMinEffort {
    
    public int minimumEffortPath(int[][] heights) {
        int[] d = {-1,0,1,0,-1};
        int n = heights.length;
        int m = heights[0].length;
        int[][] distances = new int[n][m];
        for(int i = 0;i < n;i++) Arrays.fill(distances[i], Integer.MAX_VALUE);
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->a[2]-b[2]);
        distances[0][0] = 0;
        heap.add(new int[]{0,0,0});
        while (!heap.isEmpty()) {
            int[] rec = heap.poll();
            int cx = rec[0];
            int cy = rec[1];
            int cw = rec[2];
            if(visited[cx][cy]) continue;
            if(cx == n-1 && cy == m-1) return cw;
            visited[cx][cy] = true;
            for(int k  =0,nx,ny;k < 4;k++){
                nx = cx + d[k];
                ny = cy + d[k+1];
                if(nx >= 0 && nx <= n-1 && ny >= 0 && ny <= m-1 && !visited[nx][ny]){
                    int nw = Math.max(cw, Math.abs(heights[nx][ny]-heights[cx][cy]));
                    if(nw < distances[nx][ny]){
                        distances[nx][ny] = nw;
                        heap.add(new int[]{nx,ny,nw});
                    }
                }
            }
        }
        return -1;
    }
}
