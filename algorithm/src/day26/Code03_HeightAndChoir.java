package day26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code03_HeightAndChoir {
    public static int MAXN = 1000;
    public static int[] heights = new int[MAXN];
   // public static int[][][] dp = new int[MAXN][MAXN][2];
    public static int MOD = 19650827;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for(int i = 0;i < n;i++){
                in.nextToken();
                heights[i] = (int) in.nval;

            }
            if(n == 1) out.println(1);
            else out.println(computer());
            
        }
        out.flush();
        out.close();
        br.close();
        
    }

    public static int computer(){
        int[][][] dp = new int[n][n][2];
        for(int i = 0;i <= n-2;i++) {
            if(heights[i] < heights[i+1]) dp[i][i+1][0] = dp[i][i+1][1] = 1;
        }
        for(int len = 3;len <= n;len++){
            for(int i = 0,j;i <= n - len;i++){
                j = i + len - 1;
                dp[i][j][0] = dp[i][j][1] = 0;
                
                if(heights[i] < heights[i+1]) dp[i][j][0] = (dp[i][j][0] + dp[i+1][j][0]) % MOD;
                if(heights[i] < heights[j]) dp[i][j][0] = (dp[i][j][0] + dp[i+1][j][1]) % MOD;
                if(heights[j] > heights[i]) dp[i][j][1] = (dp[i][j][1] + dp[i][j-1][0]) % MOD;
                if(heights[j] > heights[j-1]) dp[i][j][1] = (dp[i][j][1] + dp[i][j-1][1]) % MOD;
            }
        }
        return (dp[0][n-1][0] + dp[0][n-1][1])%MOD;
    }
    
}
