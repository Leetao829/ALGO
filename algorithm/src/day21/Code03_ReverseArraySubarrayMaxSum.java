package day21;
/**
 * 子数组反转之后的最大累加和问题
 */
public class Code03_ReverseArraySubarrayMaxSum {
    // 暴力方法
	// 为了验证
	public static int maxSumReverse1(int[] nums) {
		int ans = Integer.MIN_VALUE;
		for (int l = 0; l < nums.length; l++) {
			for (int r = l; r < nums.length; r++) {
				reverse(nums, l, r);
				ans = Math.max(ans, maxSum(nums));
				reverse(nums, l, r);
			}
		}
		return ans;
	}

	// nums[l...r]范围上的数字进行逆序调整
	public static void reverse(int[] nums, int l, int r) {
		while (l < r) {
			int tmp = nums[l];
			nums[l++] = nums[r];
			nums[r--] = tmp;
		}
	}

	// 返回子数组最大累加和
	public static int maxSum(int[] nums) {
		int n = nums.length;
		int ans = nums[0];
		for (int i = 1, pre = nums[0]; i < n; i++) {
			pre = Math.max(nums[i], pre + nums[i]);
			ans = Math.max(ans, pre);
		}
		return ans;
	}
    public static int maxSumReverse2(int[] nums){
        int n = nums.length;
        if(n == 0)return 0;
        int[] suffix = new int[n];
        suffix[n-1] = nums[n-1];
        for(int i = n-2;i >= 0;i--){
            suffix[i] = Math.max(nums[i], nums[i] + suffix[i+1]);
        }
        int[] preTarget = new int[n];
        int[] dp = new int[n];
        preTarget[0] = nums[0];
        dp[0] = nums[0];
        int maxT = dp[0];
        for(int i = 1;i < n;i++){
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
            preTarget[i] = Math.max(dp[i], maxT);
            maxT = Math.max(maxT, dp[i]);
        }
        int res = Integer.MIN_VALUE;
        for(int i = 1;i < n;i++){
            res = Math.max(res, suffix[i] + preTarget[i-1]);
        }
        return Math.max(res, maxT);
    }

    // 为了测试
	// 生成随机数组
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * (v * 2 + 1)) - v;
		}
		return ans;
	}

	// 为了测试
	// 对数器
	public static void main(String[] args) {
		int n = 50;
		int v = 100;
		int testTime = 20000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * n) + 1;
			int[] arr = randomArray(len, v);
			int ans1 = maxSumReverse1(arr);
			int ans2 = maxSumReverse2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}
}
