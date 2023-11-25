package day26;

import java.util.Scanner;

/**
 * 计算出使字符串匹配的最少插入次数：区间dp
 * https://www.nowcoder.com/practice/e391767d80d942d29e6095a935a5b96b
 */
public class Code01_MinimumInsertionsToMatch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] arr = str.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;// 长度为一的字符串一定需要插入一个字符
        }
        for (int i = 0, j; i < n - 1; i++) {
            // 计算长度为2的字符串的边界条件，只需要判断两边是否匹配
            j = i + 1;
            dp[i][j] = match(arr[i], arr[j]) ? 0 : 2;
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0, j; i <= n - len; i++) {
                j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                //注意，下面两种情况并不矛盾，并不是两端匹配就一定比两端不匹配的插入字符数量少
                //将每种情况进行枚举一定正确
                if (match(arr[i], arr[j])) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }

            }
        }
        System.out.println(dp[0][n - 1]);
    }

    public static boolean match(char left, char right) {
        return left == '[' && right == ']' || left == '(' && right == ')';
    }

}
