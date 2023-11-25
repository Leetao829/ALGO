package day26;
/**
 * 奇怪的打印机：区间dp，这一题比较难分析其实
 * 当两端字符相等时，只需要去掉两端其中一端即可，只需要关注剩下的n-1个字符
 * 当两端字符不相等时，需要枚举中间点，因为两边一点不在同一批次进行刷
 * https://leetcode.cn/problems/strange-printer/
 */
public class Code02_StrangePrinter {
    public int strangePrinter(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        for(int i = 0;i < n;i++){
            //一个字符一定需要刷一次
            dp[i][i] = 1;
        }
        for(int len = 2;len <= n;len++){
            for(int i = 0,j;i <= n - len;i++){
                j = i + len - 1;
                if(str[i] == str[j]) dp[i][j] = dp[i+1][j];
                else{
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i;k < j;k++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
    
}
