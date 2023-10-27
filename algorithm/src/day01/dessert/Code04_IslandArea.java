package dessert;

public class Code04_IslandArea {
    //还是使用广搜来求面积吧，广搜写的太垃圾了
    public static int MAXN = 2000;
    public static int[][] queue = new int[MAXN][2];
    public static int[] d = {-1,0,1,0,-1};
    public static int l,r;
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int ans = 0;
        for(int i = 0;i < grid.length;i++){
            for(int j = 0;j < grid[0].length;j++){
                if(grid[i][j] == 1) ans = Math.max(ans,bfs(visited,grid, i, j));
            }
        }
        return ans;
    }

    public static int bfs(boolean[][] visited,int[][] grid,int x,int y){
        int res = 0;
        l = r = 0;
        queue[r][0] = x;
        queue[r++][1] = y;
        while(l < r){
            int cx = queue[l][0];
            int cy = queue[l++][1];
            res++;
            visited[cx][cy] = true;
            grid[cx][cy] = 0;
            for(int k = 0,nx,ny;k < 4;k++){
                nx = cx + d[k];
                ny = cy + d[k+1];
                if(nx < 0 || nx > grid.length-1 || ny < 0 || ny > grid[0].length-1 || visited[nx][ny] || grid[nx][ny] == 0)continue;
                //相同的位置可能会重复出现
                queue[r][0] = nx;
                queue[r++][1] = ny;
                visited[nx][ny] = true;
            }
        }
        return res;
    }
    
}