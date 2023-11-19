package day23;
/**
 *  在字符串中插入最少字符使得字符串成为回文串，与最长回文子序列一样，划分点还是字符相等不相等
 * https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 */
public class Code02_MinimumInsertionToPalindrome {
    public int minInsertions(String s) {
        int n = s.length();
        if(n < 2) return 0;
        char[] arr = s.toCharArray();
        int[][] dp = new int[n][n];
        for(int i = 0;i < n;i++) dp[i][i] = 0;
        for(int i = 0,j;i < n-1;i++){
            j = i + 1;
            dp[i][j] = arr[i] == arr[j] ? 0 : 1;
        }
        for(int len = 3;len <= n;len++){
            for(int i = 0,j;i <= n - len;i++){
                j = i + len - 1;
                if(arr[i] == arr[j]) dp[i][j] = dp[i+1][j-1];
                else dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]) + 1;
            }
        }
        return dp[0][n-1];

    }
    
}
