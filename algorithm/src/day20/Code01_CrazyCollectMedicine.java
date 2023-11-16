package day20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 疯狂采药，完全背包板子题
 * https://www.luogu.com.cn/problem/P1616
 */
public class Code01_CrazyCollectMedicine {
    public static int MAXN = 10001;
    public static int MAXT = 10000001;
    public static int n, t;
    public static int[] costs = new int[MAXN];
    public static int[] vals = new int[MAXN];
    // 这一题的数据量有点大，需要使用空间压缩，其实空间压缩并不难写

    public static long[] dp = new long[MAXT];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {

            t = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                costs[i] = (int) in.nval;
                in.nextToken();
                vals[i] = (int) in.nval;
            }
            out.println(computer());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long computer() {
        Arrays.fill(dp, 0, t + 1, 0);
        for (int i = 1; i <= n; i++) {
            // 从左往右进行空间压缩
            for (int j = costs[i]; j <= t; j++) {
                dp[j] = Math.max(dp[j], dp[j - costs[i]] + vals[i]);
            }
        }
        return dp[t];
    }

}