package day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 混合背包问题
 */
public class Code04_MixedKnapsack {
    public static int MAXN = 101;
    public static int MAXM = 100001;
    public static int[] v = new int[MAXN];
    public static int[] c = new int[MAXN];
    // 直接使用空间压缩的技巧
    public static boolean[] dp = new boolean[MAXM];
    public static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            if (n == 0 && m == 0)
                break;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                v[i] = (int) in.nval;
            }
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                c[i] = (int) in.nval;
            }
            out.println(computer());

        }
        out.flush();
        out.close();
        br.close();

    }

    public static int computer() {
        Arrays.fill(dp, 1, m + 1, false);
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            if (c[i] == 1) {
                // 01背包，从右往左更新
                for (int j = m; j >= v[i]; j--) {
                    if (dp[j - v[i]])
                        dp[j] = true;
                }
            } else if (v[i] * c[i] > m) {
                // 完全背包，从左往右更新
                for (int j = v[i]; j <= m; j++) {
                    if (dp[j - v[i]])
                        dp[j] = true;
                }
            } else {
                // 多重背包
                for (int mod = 0; mod < v[i]; mod++) {
                    int trueCnt = 0;
                    for (int j = m - mod, size = 0; j >= 0 && size <= c[i]; size++, j -= v[i]) {
                        trueCnt += dp[j] ? 1 : 0;
                    }
                    for (int j = m - mod, l = j - (v[i] * (c[i] + 1)); j >= 1; j -= v[i], l -= v[i]) {
                        if (dp[j]) {// 表示已经是true了
                            trueCnt--;
                        } else {
                            if (trueCnt != 0) {
                                dp[j] = true;
                            }
                        }
                        if (l >= 0) {
                            trueCnt += dp[l] ? 1 : 0;
                        }
                    }
                }
            }

        }
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            if (dp[i])
                ans++;
        }
        return ans;

    }

}
