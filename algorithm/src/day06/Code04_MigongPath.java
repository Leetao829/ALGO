package day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Scanner;

public class Code04_MigongPath {
    public static int MAXN = 505;
    public static char[][] maze = new char[MAXN][MAXN];
    public static int n, m;
    public static int sx, sy, tx, ty;
    public static int[] d = { -1, 0, 1, 0, -1 };
    public static int[][] record = new int[MAXN][MAXN];
    public static boolean[][] vis = new boolean[MAXN][MAXN];

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            
            Arrays.fill(record[i], 0, m, 0);
            Arrays.fill(vis[i], 0,m,false);
            String[] strs = sc.nextLine().split(" ");
            sc.nextLine();
            for (int j = 0; j < strs.length; j++) {
                char c = strs[j].charAt(0);
                maze[i][j] = c;
                if (c == 'S') {
                    sx = i;
                    sy = j;
                }
                if (c == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }
        System.out.println(dfs(sx, sy));
    }

    // 使用记忆化搜索的方式，不然肯定爆炸
    public static int dfs(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || maze[x][y] == '#' || vis[x][y]) return -1;
        if (x == tx && y == ty) {
            record[x][y] = 0;
            return 0;
        }
        if (record[x][y] > 0) return record[x][y];
        int min = Integer.MAX_VALUE;
        vis[x][y] = true;
        for (int k = 0, nx, ny; k < 4; k++) {
            nx = x + d[k];
            ny = y + d[k + 1];
            int next = dfs(nx, ny);
            if (next != -1)
                min = Math.min(min, 1 + next);
        }
        vis[x][y] = false;
        record[x][y] = min == Integer.MAX_VALUE ? -1 : min;
        return record[x][y];
    }

    

}
