package day02;

import java.util.PriorityQueue;

/*
 * 我们发现好多问题都能够转化为heap+bfs的求解方式，就是变种的dj算法，这种算法还是很值得学习的
 */
public class Code04_SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        int[] d = {-1,0,1,0,-1};
        int n = grid.length;
        int m = grid[0].length;
        int[][] distances = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                distances[i][j] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->a[2]-b[2]);
        distances[0][0] = grid[0][0];
        heap.add(new int[]{0,0,distances[0][0]});
        while (!heap.isEmpty()) {
            int cx = heap.peek()[0];
            int cy = heap.peek()[1];
            int cw = heap.peek()[2];
            heap.poll();
            if(cx == n-1 && cy == m-1) return cw;
            visited[cx][cy] = true;
            for(int k = 0,nx,ny;k < 4;k++){
                nx = cx + d[k];
                ny = cy + d[k+1];
                if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]){
                    int nw = Math.max(cw, grid[nx][ny]);
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
