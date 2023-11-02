package day08;
//https://leetcode.cn/problems/flood-fill/description/
public class Code04_FloodFill {
    //跟简单的一道深搜的题目，就是直接写了
    public static int old;
    public static int[] d = {-1,0,1,0,-1};
    public static int n,m;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        old = image[sr][sc];
        n = image.length;
        m = image[0].length;
        if(old != color) dfs(image, sr, sc, color);
        return image;
    }
    public static void dfs(int[][] image,int x,int y,int color){
        if(x < 0 || x >= n || y < 0 || y >= m || image[x][y] != old) return;
        image[x][y] = color;
        for(int i = 0,nx,ny;i < 4;i++){
            nx = x + d[i];
            ny = y + d[i+1];
            dfs(image, nx, ny, color);
        }
    }
    
}
