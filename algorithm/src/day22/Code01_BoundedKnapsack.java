package day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 多重背包，这里不使用各种优化，只是使用最简单的枚举，没有优化
 * https://www.luogu.com.cn/problem/P1776
 */
public class Code01_BoundedKnapsack {
    public static int MAXN = 101;
    public static int MAXW = 40001;
    public static int[] c = new int[MAXN];
    public static int[] v = new int[MAXN];
    public static int[] w = new int[MAXN];
    public static int[] dp = new int[MAXW];
    public static int n,t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            t = (int) in.nval;
            for(int i = 1;i <= n;i++){
                in.nextToken();v[i] = (int) in.nval;
                in.nextToken();w[i] = (int) in.nval;
                in.nextToken();c[i] = (int) in.nval;
            }
            out.println(computer());

        }
        out.flush();
        out.close();
        br.close();
    }

    public static int computer(){
        Arrays.fill(dp, 0,t+1,0);
        for(int i = 1;i <= n;i++){
            for(int j = t;j >= 0;j--){
                //依次枚举i号商品的数量，从1-c[i]
                for(int k = 1;k <= c[i] && k * w[i] <= j;k++){
                    dp[j] = Math.max(dp[j], dp[j - k * w[i]] + v[i] * k);
                }
            }
        }
        return dp[t];
    }
    
}
