package day12;

/**
 * 删除并获取点数，统计完每个点数出现的次数之后，接下来的操作就与打家劫舍很像
 * https://leetcode.cn/problems/delete-and-earn/
 */
public class Code04_DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int[] sum = new int[10005];
        int maxn = 0;
        for (int num : nums) {
            maxn = Math.max(maxn, num);
            sum[num] += num;// 统计每一个数字的总和
        }
        // 对sum数组进行打家劫舍的算法
        int[] dp1 = new int[maxn + 1];
        int[] dp2 = new int[maxn + 1];
        for (int i = 1; i <= maxn; i++) {
            dp1[i] = sum[i] + dp2[i - 1];
            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);

        }

        return Math.max(dp1[maxn], dp2[maxn]);
    }
}
