package day26;

import java.util.Arrays;

/**
 * 统计不同回文子序列的个数：寻找划分点的可能性其实还是比较难的
 */
public class Code04_CountDifferentPalindromicSubsequences {
    public int countPalindromicSubsequences(String s) {
        int mod = 1000000007;
        char[] str = s.toCharArray();
        int n = str.length;
        //需要统计出每个字符左边和右边离他最近的位置在哪
        int[] last = new int[256];//在统计过程中每一种字符最后一次出现的位置
        int[] left = new int[n];
        Arrays.fill(last, -1);
        for(int i = 0;i < n;i++){
            left[i] = last[str[i]];
            last[str[i]] = i;
        }
        Arrays.fill(last, -1);
        int[] right = new int[n];
        for(int i = n-1;i >= 0;i--){
            right[i] = last[str[i]];
            last[str[i]] = i;
        }
        long[][] dp = new long[n][n];
        //长度为一的字符串只有一个回文子串
        for(int i = 0;i < n;i++) dp[i][i] = 1;
        //长度为2的字符串一定有两个不相同的回文子串
        for(int i = 0;i < n - 1;i++){
            dp[i][i+1] = 2;
        }
        for(int len = 3;len <= n;len++){
            for(int i = 0,j;i <= n - len;i++){
                j = i + len - 1;
                if(str[i] != str[j]){
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1] + mod;
                }
                else{
                    int l = right[i];
                    int r = left[j];
                    if(l == j && r == i){
                        //说明中间没有与两端相等的字符
                        dp[i][j] = 2 * dp[i+1][j-1] + 2;
                    }else if(l == r){
                        //说明中间只有一个与两端相等的字符
                        dp[i][j] = 2 * dp[i+1][j-1] + 1;
                    }else{
                        //说明中间有多个与两端相等的字符
                        dp[i][j] = 2 * dp[i+1][j-1] - dp[l + 1][r - 1] + mod;
                    }
                }
                dp[i][j] %= mod;
            }
        }
        return (int)dp[0][n-1];
    }
    
}
