package day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 很明显前面的不能通过所有的测试用例，因为每一次枚举的数量过多
 * 优化：使用二进制进行优化，将每一种商品及其数量进行优化，进而转换成为01背包问题
 * 能够保证使用二进制进行拆解过后，01背包能够包含所有的多重背包的情况
 */
public class Code02_BoundedKnapsackWithBinarySplitting {

    public static int MAXN = 1001;
    public static int MAXW = 40001;
    public static int[] w = new int[MAXN];
    public static int[] v = new int[MAXN];
    public static int[] dp = new int[MAXW];
    public static int n,t,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            m = 0;//用于统计总的拆解之后的商品的数量
            n = (int) in.nval;
            in.nextToken();
            t = (int) in.nval;
            for(int i = 1,value,weight,cnt;i <= n;i++){
                in.nextToken();value = (int) in.nval;
                in.nextToken();weight = (int) in.nval;
                in.nextToken();cnt = (int) in.nval;
                for(int c = 1;c <= cnt;c <<= 1){
                    v[++m] = c * value;
                    w[m] = c * weight;
                    cnt -= c;
                }
                if(cnt > 0){
                    //说明有剩余
                    v[++m] = cnt * value;
                    w[m] = cnt * weight;
                }
            }
            out.println(computer());
            
        }

        out.flush();
        out.close();
        br.close();
        
    }

    public static int computer(){
        Arrays.fill(dp, 0,t+1,0);
        for(int i = 1;i <= m;i++){
            for(int j = t;j >= w[i];j--){
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        return dp[t];
    }
    
}
