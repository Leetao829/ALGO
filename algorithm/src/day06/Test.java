package day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static int n,m,t,sx,sy,tx,ty;
    public static int[][] maze = new int[10][10];
    public static int[][] record  =new int[10][10];
    public static boolean[][] vis = new boolean[10][10];
    public static int[] d = {-1,0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            in.nextToken();
            m = (int)in.nval;
            in.nextToken();
            for(int i = 0;i < n;i++){
                Arrays.fill(maze, 1,m+1,0);
                Arrays.fill(record, 1,m+1,0);
                Arrays.fill(vis, 1,m+1,false);
            }
            t = (int)in.nval;
            in.nextToken();
            sx = (int)in.nval;
            in.nextToken();
            sy = (int)in.nval;
            in.nextToken();
            tx = (int)in.nval;
            in.nextToken();
            ty = (int)in.nval;
            for(int i = 0,x,y;i < t;i++){
                in.nextToken();
                x = (int)in.nval;
                in.nextToken();
                y = (int)in.nval;
                maze[x][y] = 1;
            }
            int res = dfs(sx, sy);
            out.println(res);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int dfs(int x, int y) {
        if (x == tx && y == ty) {
            record[x][y] = 0;
            return 0;
        }

        if (record[x][y] != 0)
            return record[x][y];
        int min = Integer.MAX_VALUE;
        vis[x][y] = true;
        for (int k = 0, nx, ny; k < 4; k++) {
            nx = x + d[k];
            ny = y + d[k + 1];
            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m && !vis[nx][ny]) {
                int next = dfs(nx, ny);
                if (next != -1)
                    min = Math.min(min, 1 + next);
            }

        }
        vis[x][y] = false;
        record[x][y] = min == Integer.MAX_VALUE ? -1 : min;
        return record[x][y];
    }


}
