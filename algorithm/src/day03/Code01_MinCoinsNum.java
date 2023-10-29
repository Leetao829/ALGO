package day03;

import java.util.Arrays;

public class Code01_MinCoinsNum {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i = 1;i <= amount;i++){
            for(int j = 0;j < coins.length;j++){
                if(coins[j] <= i) dp[i] = Math.min(dp[i], 1+dp[i-coins[j]]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    //还是来写一下记忆化搜索吧，这方面确实不怎么熟练
    public int coinChange2(int[] coins,int amount){
        if(amount <= 0)return 0;
        int[] count = new int[amount+1];
        return dfs(coins, count, amount);
    }

    public static int dfs(int[] coins,int[] count,int amount){
        if(amount < 0)return -1;
        if(amount == 0){
            return 0;
        }
        if(count[amount] != 0) return count[amount];
        int min = Integer.MAX_VALUE;
        for(int coin : coins){
            int tmp = dfs(coins, count, amount-coin);
            if(tmp != -1){
                min = Math.min(1+tmp, min);
            }
        }
        count[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return count[amount];
    }
}
