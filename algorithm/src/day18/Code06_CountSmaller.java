package day18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个数组，请返回另一个数组，这个数组中每一个位置代表的含义是：当前位置往右小于当前位置数字的个数
 * 思路，使用线段树，找到数组中的最小值min与最大值max，以[min,max]区间构建线段树，
 * 反向遍历原始数组，每遍历到一个数值，就对线段树[nums[i],nums[i]]区间加一，并同时统计[min,nums[i]-1]区间中的个数
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
 */
public class Code06_CountSmaller {
    public static int MAXN = 20001;
    // 创建sum数组
    public static int[] sum = new int[MAXN << 2];
    // 懒加载
    public static int[] lazy = new int[MAXN << 2];
    public static int minNum, maxNum;

    public static List<Integer> countSmaller(int[] nums) {
        minNum = Integer.MAX_VALUE;
        maxNum = Integer.MIN_VALUE;
        // 先求解最大值与最小值
        for (int num : nums) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
        }
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 0)
            return ans;
        if (maxNum == minNum) {
            // 数组中所有数值相等，直接全部赋值为零
            for (int i = 0; i < n; i++)
                ans.add(0);
            return ans;
        }
        build();
        // 开始遍历，在遍历的过程中构建线段树，并查询

        for (int i = n - 1; i >= 0; i--) {
            add(nums[i], nums[i], 1, minNum, maxNum, 1);
            if (minNum > nums[i] - 1)
                ans.add(0);
            else
                ans.add(query(minNum, nums[i] - 1, minNum, maxNum, 1));
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void build() {
        for (int i = 1; i <= ((maxNum - minNum + 1) * 4 + 1); i++) {
            sum[i] = 0;
            lazy[i] = 0;
        }
    }

    public static void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    public static void pushDown(int rt, int ln, int rn) {
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * ln;
            sum[rt << 1 | 1] += lazy[rt] * rn;
        }
    }

    public static int query(int L, int R, int l, int r, int rt) {
        if (L <= l && R >= r) {
            return sum[rt];
        }
        int ans = 0;
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid)
            ans += query(L, R, l, mid, rt << 1);
        if (R > mid)
            ans += query(L, R, mid + 1, r, rt << 1 | 1);
        return ans;
    }

    public static void add(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && R >= r) {
            sum[rt] += (r - l + 1) * C;
            lazy[rt] += C;
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid)
            add(L, R, C, l, mid, rt << 1);
        if (R > mid)
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }

}
