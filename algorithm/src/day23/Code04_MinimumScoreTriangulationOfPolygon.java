package day23;

/**
 * 将多边形进行划分，能够得到的三角形得分的最大值，
 * 关键是找到划分点，再进行区间dp
 * https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/
 */
public class Code04_MinimumScoreTriangulationOfPolygon {

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        //根据位置依赖填dp表
        for(int len = 3;len <= n;len++){
            for(int i = 0,j;i <= n - len;i++){
                j = i + len - 1;
                if(len == 3){
                    dp[i][j] = values[i] * values[i+1] * values[i+2];
                }else{
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i + 1;k < j;k++){
                        dp[i][j] = Math.min(dp[i][j], values[i] * values[k] * values[j] + dp[i][k] + dp[k][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}