package day19;
/**
 * 给定一个数组，求出其子序列中，能够被7整除的最大子序列的累加和是多少
 * 
 * 很巧妙的一道动态规划题目，dp[i][j]：前i个数字中自由选择，必须做到累加值和%7==j时，最大累加和为多少
 * 注意：取模之后的余数必须为j
 * 很像一道01背包
 */
public class Code05_MaxSumDivideBy7 {

    // 暴力方法
	// 为了验证
	public static int maxSum1(int[] nums) {
		// nums形成的所有子序列的累加和都求出来
		// 其中%7==0的那些累加和中，返回最大的
		// 就是如下f函数的功能
		return f(nums, 0, 0);
	}

	public static int f(int[] nums, int i, int s) {
		if (i == nums.length) {
			return s % 7 == 0 ? s : 0;
		}
		return Math.max(f(nums, i + 1, s), f(nums, i + 1, s + nums[i]));
	}

    public static int maxSumDivide(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n+1][7];
        dp[0][0] = 0;
        for(int i = 1;i < 7;i++) dp[0][i] = -1;//不存在
        for(int i = 1;i <= n;i++){
            int cur = nums[i-1] % 7;
            for(int j = 0,need;j < 7;j++){
                dp[i][j] = dp[i-1][j];
                need = j - cur >= 0 ? j - cur : j - cur + 7;
                if(dp[i-1][need] != -1) dp[i][j] = Math.max(dp[i][j], dp[i-1][need] + nums[i-1]);
            }
        }
        return dp[n][0];
    }

    // 为了测试
	// 生成随机数组
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v);
		}
		return ans;
	}

	// 为了测试
	// 对数器
	public static void main(String[] args) {
		int n = 15;
		int v = 30;
		int testTime = 20000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * n) + 1;
			int[] nums = randomArray(len, v);
			int ans1 = maxSum1(nums);
			int ans2 = maxSumDivide(nums);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}
    
}
