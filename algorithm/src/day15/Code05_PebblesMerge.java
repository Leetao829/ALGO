package day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 石子合并，这一次是题目中有环，思路是将环拆成链，分别求出每一条链的最大值
 * https://www.luogu.com.cn/problem/P1880
 */
public class Code05_PebblesMerge {
    public static int MAXN = 102;
    public static int[] pebbles = new int[2 * MAXN];
    public static int[] sum = new int[2 * MAXN];
    public static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int m = 0;
        while (m++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {

            n = (int) in.nval;
            for(int i = 1;i <= n;i++){
                in.nextToken();
                pebbles[i] = (int) in.nval;
                pebbles[i+n] = (int) in.nval;
                sum[i] = sum[i-1] + pebbles[i];
            }
            for(int i = n+1;i <= 2 * n;i++){
                sum[i] = sum[i-1] + pebbles[i];
            }
            int[][] dp1 = new int[2 * n][2 * n];
            int[][] dp2 = new int[2 * n][2 * n];
            int res1 = Integer.MAX_VALUE;
            int res2 = Integer.MIN_VALUE;
            for(int start = 1,end;start <= n;start++){
                end = start + n - 1;//确定大小区间
                for(int len = 2;len <= n;len++){
                    for(int i = start,j;i <= end - len + 1;i++){
                        j = i + len - 1;
                        dp1[i][j] = Integer.MAX_VALUE;
                        dp2[i][j] = Integer.MIN_VALUE;
                        for(int k = i;k < j;k++){
                            dp1[i][j] = Math.min(dp1[i][j], dp1[i][k] + dp1[k+1][j] + sum[j] - sum[i-1]);
                            dp2[i][j] = Math.max(dp2[i][j], dp2[i][k] + dp2[k+1][j] + sum[j] - sum[i-1]);
                        }
                    }
                }
                res1 = Math.min(res1, dp1[start][end]);
                res2 = Math.max(res2, dp2[start][end]);

            }
            out.println(res1);
            out.println(res2);
        }
        out.flush();
        out.close();
        br.close();
    }
    
}
