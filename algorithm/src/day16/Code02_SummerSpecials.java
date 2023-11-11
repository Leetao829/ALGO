package day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 夏季特惠，变种的01背包问题
 */
public class Code02_SummerSpecials {
    public static int MAXN = 505;
    public static int n,x;
    public static int[] vals = new int[MAXN];
    public static int[] costs = new int[MAXN];
    public static int res,oldPrice,nowPrice,funs;
    public static int index;//表示需要背包的商品的下标

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int m = 0;
        while (m++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            x = (int) in.nval;
            res = 0;
            index = 1;//从1开始
            for(int i = 0;i < n;i++){
                in.nextToken();
                oldPrice = (int)in.nval;
                in.nextToken();
                nowPrice = (int) in.nval;
                in.nextToken();
                funs = (int) in.nval;
                if(oldPrice - nowPrice - nowPrice >= 0){
                    //说明这样的商品能够增加心理预期，是一定需要购买的商品
                    x += (oldPrice-nowPrice-nowPrice);
                    res += funs;
                }else{
                    //这样的商品是需要使用背包的商品
                    costs[index] = nowPrice-(oldPrice - nowPrice);
                    vals[index++] = funs;

                }
            }
            //在这里创建dp表
            int[] dp = new int[x+2];
            res += computer(dp);


            out.println(res);
        }

        out.flush();
        out.close();
        br.close();
        
    }
    public static int computer(int[] dp){
        Arrays.fill(dp, 0);
        for(int i = 1;i <= index;i++){
            for(int j = x;j >= 0;j--){
                if(j - costs[i] >= 0){
                    dp[j] = Math.max(dp[j],dp[j-costs[i]] + vals[i]);
                }
            }
        }
        return dp[x];
    }
    
}
