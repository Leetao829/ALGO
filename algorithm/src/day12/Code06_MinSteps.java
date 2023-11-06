package day12;
/**
 * https://leetcode.cn/problems/2-keys-keyboard/description/
 */
public class Code06_MinSteps {
    public int minSteps(int n) {
        int[] dp = new int[n+1];
        for(int i = 2;i <= n;i++){
            for(int j = 1;j * j <= i;j++){
                if(i % j == 0){
                    dp[i] = Math.min(dp[i],dp[j] + i / j);
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];

    }
}
