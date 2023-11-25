package day26;
/**
 * 移动盒子获得积分，因为是需要消去连续相同数字的盒子，其中用到了贪心的思想，
 * 记忆化搜索+贪心
 */
public class Code05_RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] rec = new int[n][n][n];
        return dfs(boxes, 0, n-1, 0, rec);
    }

    /*
     * 在[l..r]中，其中k表示box[l]之前有k个与box[l]相同数字的个数，
     * 这里需要枚举与前k个box[l]能够相连的数字位置，最后就一定能够获得最大的积分
     * 其中用到了贪心的思想
     */
    public static int dfs(int[] boxes,int l,int r,int k,int[][][] rec){
        if(l > r) return 0;
        if(rec[l][r][k] > 0) return rec[l][r][k];
        //下面寻找中间与l位置相等的最远位置
        int s = l;
        while (s + 1 <= r && boxes[s+1] == boxes[l]) {
            s++;
        }
        int cnt = k + s - l + 1;
        //策略一：直接与前面的串拼接
        int ans = cnt * cnt + dfs(boxes, s + 1, r, 0, rec);
        //s+1位置的元素一定与l位置元素不相同
        //从s+2位置往后寻找能够与l位置拼接的元素
        for(int m = s + 2;m <= r;m++){
            if(boxes[m] == boxes[l] && boxes[m] != boxes[m-1]){
                ans = Math.max(ans, dfs(boxes, s + 1, m - 1, 0, rec) + dfs(boxes, m, r, cnt, rec));
            }
        }
        rec[l][r][k] = ans;
        return ans;
    }
}
