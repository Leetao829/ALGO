package day21;

/**
 * 找到数组中三个长度为k的无重复子数组之和最大值
 */
public class Code06MaximumSum3UnoverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // 先进行预处理，以i开始的长度为k的子数组累加之和
        int n = nums.length;
        int[] sum = new int[n];
        // 创建左闭右开的窗口
        int l = 0;
        int r = l + k;
        int add = 0;
        for (int i = l; i < r; i++)
            add += nums[i];
        sum[l] = add;
        while (r < n) {
            add -= nums[l++];
            add += nums[r++];
            sum[l] = add;
        }

        // 预处理，得到[0,i]上长度为k的子数组最大值，这里只用维护下标即可
        int[] pre = new int[n];
        l = 0;
        r = l + k;
        pre[r - 1] = l;
        int maxPre = sum[l];
        while (r < n) {
            pre[r] = pre[r - 1];
            r++;
            l++;
            if (sum[l] > maxPre) {
                maxPre = sum[l];
                pre[r - 1] = l;
            }
        }
        // 还需要维护[i,n]上长度为k的子数组最大值，还是只需要维护下标即可
        r = n;
        l = r - k;
        int[] suff = new int[n];
        suff[l] = l;
        int maxSuff = sum[l];
        while (l > 0) {
            l--;
            suff[l] = suff[l + 1];
            if (sum[l] >= maxSuff) {
                maxSuff = sum[l];
                suff[l] = l;
            }

        }
        int maxV = Integer.MIN_VALUE;
        int l1 = -1, l2 = -1, l3 = -1;
        //注意这里i的取值范围
        for (int i = k; i <= n - 2 * k; i++) {
            int tmp = sum[i] + sum[pre[i - 1]] + sum[suff[i + k]];
            if (tmp > maxV) {
                l1 = pre[i - 1];
                l3 = suff[i + k];
                l2 = i;
                maxV = tmp;
            }
        }
        return new int[] { l1, l2, l3 };
    }

}
