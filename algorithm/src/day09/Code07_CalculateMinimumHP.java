package day09;

import java.util.Arrays;

/**
 * 地下城游戏
 * https://leetcode.cn/problems/dungeon-game/
 */
public record Code07_CalculateMinimumHP() {
    // public static int path;//统计遍历过程中的每一条路径的前缀
    // public static int minN;//统计每一条路径上的最小前缀
    // public static int res;//这个空间爆掉了哈哈，还是使用临时变量存储中间最大值笨蛋
    // public static int index,n,m;
    // public int calculateMinimumHP(int[][] dungeon) {
    // n = dungeon.length;
    // m = dungeon[0].length;
    // index = 0;
    // minN = dungeon[0][0];
    // path = dungeon[0][0];
    // res = Integer.MIN_VALUE;
    // dfs(dungeon, 0, 0);

    // return res > 0 ? 1 : 1-res;

    // }
    // public static void dfs(int[][] dungeon,int x,int y){
    // if(x == n-1 && y == m-1){
    // res = Math.max(res, minN);
    // return;
    // }
    // if(y + 1 < m){
    // path += dungeon[x][y+1];
    // int tmp = minN;
    // minN = Math.min(minN, path);
    // dfs(dungeon, x, y+1);
    // path-=dungeon[x][y+1];
    // minN = tmp;
    // }
    // if(x + 1 < n){
    // path += dungeon[x+1][y];
    // int tmp = minN;
    // minN = Math.min(minN, path);
    // dfs(dungeon, x+1, y);
    // path-=dungeon[x+1][y];
    // minN = tmp;
    // }

    // }

    // 答案还是很厉害的，使用了从终点到原点的动态规划优化掉了我的带路径的深度优先搜索
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n+1][m+1];
        for(int i = 0;i <=n ;i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[n][m-1] = dp[n-1][m] = 1;
        for(int i = n-1;i >= 0;i--){
            for(int j = m-1;j >= 0;j--){
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j],dp[i][j+1])-dungeon[i][j]);
            }
        }
        return dp[0][0];

    }

}

/**
 * 
 */
