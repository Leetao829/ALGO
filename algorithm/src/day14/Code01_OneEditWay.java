package day14;
/**
 * 编辑距离
 * https://leetcode.cn/problems/one-away-lcci/
 * 其实这一题本能的想使用动态规划进行求解
 */
public class Code01_OneEditWay {
    // 动态规划求解
    public boolean oneEditAway(String first, String second) {
        int n = first.length();
        int m = second.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++)
            dp[0][i] = i;
        for (int i = 0; i <= n; i++)
            dp[i][0] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
            }
        }
        return dp[n][m] <= 1;
    }

    public boolean oneEditAway1(String first, String second) {
        // 可能使用双指针更简单，毕竟题目只要求一次或零次编辑
        int n = first.length();
        int m = second.length();
        if (Math.abs(n - m) >= 2)
            return false;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (first.charAt(i) != second.charAt(j)) {
                return method(first, second, i + 1, j) ||
                        method(first, second, i, j + 1) ||
                        method(first, second, i + 1, j + 1);
            }
            i++;
            j++;

        }
        return i == j;
    }

    public static boolean method(String first, String second, int i, int j) {
        if (first.length() - i != second.length() - j)
            return false;
        while (i < first.length() && j < second.length()) {
            if (first.charAt(i) != second.charAt(j))
                return false;
            i++;
            j++;

        }
        return true;
    }

}
