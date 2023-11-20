package day24;
/**
 * 戳气球：区间dp，这一题与切棍子很像，只不过这一题需要按照最后一次戳爆的气球进行枚举，因为如果按照第一次的话，
 * 左右状态会相互影响
 */
public class Code02_BurstBallons {
    public int maxCoins(int[] nums){
        int n = nums.length;
        int[] costs = new int[n+2];
        costs[0] = costs[n+1] = 1;
        for(int i = 1;i <= n;i++) costs[i] = nums[i-1];
        int[][] dp = new int[n+2][n+2];
        for(int i = 1;i <= n;i++) dp[i][i] = costs[i-1] * costs[i] * costs[i+1];
        for(int len = 2;len <= n;len++){
            for(int i = 1,j;i <= n - len + 1;i++){
                j = i + len - 1;
                dp[i][j] = Integer.MIN_VALUE;
                for(int k = i,val;k <= j;k++){
                    val = costs[k] * costs[i-1] * costs[j+1];
                    if(k == i) dp[i][j] = Math.max(dp[i][j], dp[i+1][j] + val);
                    else if(k == j) dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + val);
                    else dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + dp[k+1][j] + val);
                }
            }
        }
        return dp[1][n];
    }
}
