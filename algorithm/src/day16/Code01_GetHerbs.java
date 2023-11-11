package day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;


/**
 * 背包dp的板子题
 * https://www.luogu.com.cn/problem/P1048
 */
public class Code01_GetHerbs {
    public static int MAXN = 105;
    public static int MAXM = 1005;
    public static int[] costs = new int[MAXN];
    public static int[] vals = new int[MAXN];
    //dp含义：表示在前i个物品中自由选择，总花费不超过j的能获得的最大价值，在i位置上有选与不选两种选择，01背包问题
    //显然dp[i][j] = Max(dp[i-1][j],dp[i-1][j-costs[i]]+vals[i])
   // public static int[][] dp = new int[MAXN][MAXM];
    public static int[] dp2 = new int[MAXM];//对二维表使用空间压缩
    public static int t,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int n = 0;
        while (n++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            t = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for(int i = 1;i <= m;i++){
                in.nextToken();
                costs[i] = (int) in.nval;
                in.nextToken();
                vals[i] = (int) in.nval;
            }
            method2();
            out.println(dp2[t]);
            
        }
        out.flush();
        out.close();
        br.close();
        
    }

    // public static void method1(){
    //     //在填写dp表时，需要每一行需要从右往左填写
    //     //当j-costs[i]<0时，不需要进行考虑
    //     for(int i = 1;i <= m;i++){
    //         for(int j = t;j >= 0;j--){
    //             dp[i][j] = dp[i-1][j];
    //             if(j - costs[i] >= 0){
    //                 dp[i][j] = Math.max(dp[i][j], dp[i-1][j-costs[i]]+vals[i]);
    //             }
    //         }
    //     }
    // }

    public static void method2(){
        Arrays.fill(dp2, 0,t+1,0);
        for(int i = 1;i <= m;i++){
            for(int j = t;j >= 0;j--){
                if(j-costs[i] >= 0){
                    dp2[j] = Math.max(dp2[j], dp2[j-costs[i]] + vals[i]);
                }
            }
        }
    }
    
}
