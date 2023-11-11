package day16;
/**
 * 目标和
 * https://leetcode.cn/problems/target-sum/
 */
public class Code03_TargetSum {
    public static int res;
    public int findTargetSumWays(int[] nums, int target) {
        res = 0;
    //    sum = 0;
        dfs(nums, 0, target, 0);
        return res;

    }
    public static void dfs(int[] nums,int i,int target,int sum){
        if(i == nums.length) {
            if(sum == target) res++;
            return;
        }
        int tmp = sum;
        sum+=nums[i];
        dfs(nums, i+1, target, sum);
        sum = tmp;
        sum-=nums[i];
        dfs(nums, i+1, target, sum);

    }

    //递归地另一种写法
    public static int dfs2(int[] nums,int target,int i,int sum){
        if(i == nums.length) return sum == target ? 1 : 0;
        return dfs2(nums, target, i+1, sum + nums[i]) + dfs2(nums, target, i+1, sum - nums[i]);
    }



    /**
     * 将问题转换成为01背包问题
     * dp[i][j]:在前i个数字中自由选择，目标和为target的个数
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays1(int[] nums,int target){
        //首先，所有数字的累加和应该与target的奇偶性相同
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum < target || ((sum & 1) ^ (target & 1)) == 1) return 0;
        return computer(nums, (target + sum) >> 1);
    }
    public static int computer(int[] nums,int t){
        int[] dp = new int[t+1];
        dp[0] = 1;
        for(int num : nums){
            for(int j = t;j >= num;j--){
                dp[j] += dp[j-num];
            }
        }
        return dp[t];
    }
    
}
