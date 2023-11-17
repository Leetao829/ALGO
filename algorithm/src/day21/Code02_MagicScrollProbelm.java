package day21;

/**
 * 魔法卷轴：你可以使用两次魔法卷轴，将数组中指定区间的子数组全部刷成零
 * 希望数组整体的累加和最大
 */
public class Code02_MagicScrollProbelm {
    // 暴力方法
    // 为了测试
    public static int maxSum1(int[] nums) {
        int p1 = 0;
        for (int num : nums) {
            p1 += num;
        }
        int n = nums.length;
        int p2 = mustOneScroll(nums, 0, n - 1);
        int p3 = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            p3 = Math.max(p3, mustOneScroll(nums, 0, i - 1) + mustOneScroll(nums, i, n - 1));
        }
        return Math.max(p1, Math.max(p2, p3));
    }

    // 暴力方法
    // 为了测试
    // nums[l...r]范围上一定要用一次卷轴情况下的最大累加和
    public static int mustOneScroll(int[] nums, int l, int r) {
        int ans = Integer.MIN_VALUE;
        // l...r范围上包含a...b范围
        // 如果a...b范围上的数字都变成0
        // 返回剩下数字的累加和
        // 所以枚举所有可能的a...b范围
        // 相当暴力，但是正确
        for (int a = l; a <= r; a++) {
            for (int b = a; b <= r; b++) {
                // l...a...b...r
                int curAns = 0;
                for (int i = l; i < a; i++) {
                    curAns += nums[i];
                }
                for (int i = b + 1; i <= r; i++) {
                    curAns += nums[i];
                }
                ans = Math.max(ans, curAns);
            }
        }
        return ans;
    }

    public static int maxSum2(int[] nums) {
        if (nums.length == 0)
            return 0;
        // 情况一：不使用卷轴
        int res1 = 0;
        for (int num : nums)
            res1 += num;
        // 情况二：使用一次魔法卷轴
        int n = nums.length;
        // 先求出以i为结尾的最长前缀和(注意是最长，就是递增的，因为卷轴会将中间的全部数字刷成零)
        int sum1 = nums[0];
        int[] prefix = new int[n];
        int maxPre = Math.max(nums[0], 0);
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1] + nums[i], maxPre);
            sum1 += nums[i];
            maxPre = Math.max(maxPre, sum1);
        }

        int res2 = prefix[n - 1];
        int sum2 = nums[n - 1];
        int[] suffix = new int[n];
        int maxSuff = Math.max(nums[n - 1], 0);
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(maxSuff, suffix[i + 1] + nums[i]);
            sum2 += nums[i];
            maxSuff = Math.max(maxSuff, sum2);
        }
        // 使用两次魔法卷轴
        int res3 = Integer.MIN_VALUE;
        for (int i = 1; i < n - 1; i++) {
            res3 = Math.max(res3, prefix[i - 1] + suffix[i]);
        }
        return Math.max(res1, Math.max(res2, res3));
    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * (v * 2 + 1)) - v;
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int n = 50;
        int v = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n);
            int[] nums = randomArray(len, v);
            int ans1 = maxSum1(nums);
            int ans2 = maxSum2(nums);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
