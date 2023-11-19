package day23;
/**
 * 最长回文子序列问题
 * 经典动态规划，关键是能够找到尝试的点
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 */
public class Code01_LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s){
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i = 0;i < n;i++) dp[i][i] = 1;
        for(int i = 0,j;i < n-1;i++){
            j = i + 1;
            dp[i][j] = arr[i] == arr[j] ? 2 : 1;
        }
        for(int len = 3;len <= n;len++){
            for(int i = 0,j;i <= n - len;i++){
                j = i + len - 1;
                if(arr[i] == arr[j]) dp[i][j] = 2 + dp[i+1][j-1];
                else dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][n-1];
    }
    
}
