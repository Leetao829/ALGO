package day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 石子合并，使用区间dp
 */
public class Code04_PebblesMerge {
    public static int MAXN = 305;
    public static int[] pebbles = new int[MAXN];
    public static int n;
    public static int[] sum = new int[MAXN];//前缀和，用于统计合并时的代价

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int m = 0;
        while(m++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for(int i = 1;i <= n;i++){
                in.nextToken();
                pebbles[i] = (int) in.nval;
                sum[i] = sum[i-1]+pebbles[i];
            }
            int[][] dp = new int[MAXN][MAXN];
            //一堆石子合并时代价为零，在这里不用考虑
            for(int len = 2;len <= n;len++){
                for(int i = 1,j;i <= n - len + 1;i++){
                    
                    j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i;k <= j;k++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j]+sum[j]-sum[i-1]);
                    }
                }
            }
            out.println(dp[1][n]);

        }
        out.flush();
        out.close();
        br.close();
        
    }
}