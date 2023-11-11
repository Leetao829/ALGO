package day15;
/**
 * 检查有效的括号字符串，对区间使用动态规划进行求解
 * https://leetcode.cn/problems/valid-parenthesis-string/
 */
public class Code01_CheckValidString {
    public boolean checkValidString(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = 0;i < n;i++){
            //长度1的边界情况
            if(s.charAt(i) == '*') dp[i][i] = true;
        }
        //长度为2的边界情况
        for(int i = 0;i < n-1;i++){
            if((s.charAt(i) == '(' || s.charAt(i) == '*') && (s.charAt(i+1) == ')' || s.charAt(i+1) == '*')) dp[i][i+1] = true;
        }
        //对所有长度进行枚举
        for(int len = 3;len <= n;len++){
            for(int i = 0,j;i <= n-len;i++){
                j = i + len - 1;
                if((s.charAt(i) == '(' || s.charAt(i) == '*') && (s.charAt(j) == ')' || s.charAt(j) == '*')) dp[i][j] = dp[i+1][j-1];
                if(dp[i][j]) continue;
                for(int k = i;k < j;k++){
                    if(dp[i][k] && dp[k+1][j]){
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        
        return dp[0][n-1];
    }
    
}
