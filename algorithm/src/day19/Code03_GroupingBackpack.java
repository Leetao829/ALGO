package day19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 分组背包问题：与01背包相比多了更多的可能性
 */
public class Code03_GroupingBackpack {
    public static int MAXN = 1001;
    public static int MAXM = 1001;
    
    public static int[] dp = new int[MAXM];
    public static int[][] goods = new int[MAXN][3];
    public static int n, m;
    



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            m = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                goods[i][0] = (int) in.nval;
                in.nextToken();
                goods[i][1] = (int) in.nval;
                in.nextToken();
                goods[i][2] = (int) in.nval;
            }
            // 先将商品按照组进行排序
            Arrays.sort(goods, 1, n + 1, (a, b) -> a[2] - b[2]);
            int res = method1();
            out.println(res);

        }
        out.flush();
        out.close();
        br.close();

    }

    public static int method() {
        // 先计算组的数量
        int groups = 1;
        for (int i = 2; i <= n; i++) {
            if (goods[i][2] != goods[i - 1][2])
                groups++;
        }
        // 实现背包dp
        int[][] dp = new int[groups + 1][m + 1];
        for (int i = 1, start = 1, end = 2; start <= n; i++) {
            // 先确定分组的下标
            while (end <= n && goods[end][2] == goods[start][2])
                end++;
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];// 表示当前组中元素一个也不选择
                for (int k = start; k < end; k++) {
                    // 分别选择组内的商品
                    if (j - goods[k][0] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[k][0]] + goods[k][1]);
                    }
                }
            }
            start = end++;

        }
        return dp[groups][m];

    }

    //使用空间压缩形式的分组dp
    public static int method1(){
        Arrays.fill(dp, 1,m+1,0);
        int groups = 1;
        for(int i = 2;i <= n;i++){
            if(goods[i-1][2] != goods[i][2]) groups++;
        }
        for(int i = 1,starts = 1,end = 2; starts <= n;i++){
            while (end <= n && goods[end][2] == goods[starts][2]) {
                end++;
            }
            for(int j = m;j >= 0;j--){
                for(int k = starts;k < end;k++){
                    if(j - goods[k][0] >= 0){
                        dp[j] = Math.max(dp[j],dp[j-goods[k][0]] + goods[k][1]);
                    }
                }
            }
            starts = end++;
        }
        return dp[m];
    }
}
