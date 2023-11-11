package day15;
/**
 * 交错字符串：dp[i][j]表示s1的前i个字符与s2的前j个字符能否组成s3的i+j个字符
 * https://leetcode.cn/problems/interleaving-string/description/
 */
public class Code02_InterLeaveString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        if(n + m != s3.length()) return false;
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int i = 0;i <= n;i++){
            for(int j = 0;j <= m;j++){
                int p = i + j;
                if(i > 0) dp[i][j] |= (s3.charAt(p-1) == s1.charAt(i-1) && dp[i-1][j]);
                if(j > 0) dp[i][j] |= (s3.charAt(p-1) == s2.charAt(j-1) && dp[i][j-1]);
            }
        }
        return dp[n][m];
    }
}
