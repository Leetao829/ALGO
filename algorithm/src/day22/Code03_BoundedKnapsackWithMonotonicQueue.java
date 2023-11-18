package day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 使用优先队列对多重背包进行优化，这个难度还是很大的
 */
public class Code03_BoundedKnapsackWithMonotonicQueue {
    public static int MAXN = 101;
    public static int MAXW = 40001;
    public static int[] v = new int[MAXN];
    public static int[] w = new int[MAXN];
    public static int[] c = new int[MAXN];
    // 创建优先级队列
    public static int[] queue = new int[MAXW];
    public static int[] dp = new int[MAXW];
    public static int l, r;
    public static int n, t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            t = (int) in.nval;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                v[i] = (int) in.nval;
                in.nextToken();
                w[i] = (int) in.nval;
                in.nextToken();
                c[i] = (int) in.nval;
            }
            out.println(computer());

        }
        out.flush();
        out.close();
        br.close();
    }

    /**
     * 使用严格位置依赖的动态规划，这个就不好写了
     * 
     * @return
     */
    public static int computer() {
        int[][] dp = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            // 将没一行按照余数进行分组，在每个分组中使用优先级队列
            for (int mod = 0; mod <= Math.min(t, w[i] - 1); mod++) {
                l = r = 0;
                // 在每一个分组中使用优先级队列
                for (int j = mod; j <= t; j += w[i]) {
                    // 优先级队列是由大到小（从左往右）
                    while (l < r && dp[i - 1][queue[r - 1]] + inc(j - queue[r - 1], i) <= dp[i - 1][j]) {
                        r--;
                    }
                    queue[r++] = j;// 优先级队列中存储的是下标

                    // 需要更新过期的下标
                    if (queue[l] == j - w[i] * (c[i] + 1))
                        l++;
                    // while(queue[l] <= j - w[i] * (c[i] + 1)) l++;这样写也可以
                    dp[i][j] = dp[i - 1][queue[l]] + inc(j - queue[l], i);
                }

            }
        }
        return dp[n][t];
    }

    /**
     * 使用空间压缩的版本
     * @return
     */
    public static int compute2() {
        for (int i = 1; i <= n; i++) {
            for (int mod = 0; mod <= Math.min(t, w[i] - 1); mod++) {
                // 因为空间压缩时，关于j的遍历是从右往左，而不是从左往右
                // 所以先让dp[i-1][...右侧的几个位置]进入窗口
                l = r = 0;
                for (int j = t - mod, k = 0; j >= 0 && k <= c[i]; j -= w[i], k++) {
                    while (l < r && dp[j] + inc(queue[r - 1] - j, i) >= dp[queue[r - 1]]) {
                        r--;
                    }
                    queue[r++] = j;
                }
                // 然后j开始从右往左遍历
                // 注意，因为j从右往左遍历，所以：
                // 更靠右的j位置更早进入窗口
                // 更靠左的j位置更晚进入窗口
                for (int j = t - mod; j >= 0; j -= w[i]) {
                    // 来到j，计算dp[i][j]的值，做了空间压缩，所以去更新dp[j]
                    dp[j] = dp[queue[l]] + inc(j - queue[l], i);
                    // 求解完dp[j]
                    // 接下来要去求解dp[j-w[i]]了(根据余数分组)
                    // 所以看看窗口最左的下标是不是j(其实代表dp[i-1][j]的值]
                    // 是的话，说明最左下标过期了，要弹出
                    if (queue[l] == j) {
                        l++;
                    }
                    // 最后
                    // 因为接下来要去求解dp[j-w[i]]了
                    // 所以新的dp[i-1][enter]要进入窗口了
                    // 用单调队列的更新方式让其进入
                    int enter = j - w[i] * (c[i] + 1);
                    if (enter >= 0) {
                        while (l < r && dp[enter] + inc(queue[r - 1] - enter, i) >= dp[queue[r - 1]]) {
                            r--;
                        }
                        queue[r++] = enter;
                    }
                }
            }
        }
        return dp[t];
    }

    public static int inc(int s, int i) {
        return s / w[i] * v[i];
    }

}
