package day18;

import java.util.Arrays;

/**
 * 实现线段树中的基本操作
 * 不得不说，线段树基本上算是最复杂的数据结构了
 */
public class Code05_SegmentTree {
    public static int MAXN = 1000;
    public static int[] arr = new int[MAXN];
    public static int[] sum = new int[MAXN << 2];// 需要开4倍的大小
    public static int[] lazy = new int[MAXN << 2];// 懒加载
    public static int[] change = new int[MAXN << 2];
    public static boolean[] update = new boolean[MAXN << 2];

    /*
     * 传入原始数组进行初始化
     */
    public static void init(int[] origins) {
        int n = origins.length;
        for (int i = 1; i <= n; i++)
            arr[i] = origins[i - 1];
        Arrays.fill(sum, 0, 4 * n, 0);
        Arrays.fill(lazy, 0, 4 * n, 0);
        Arrays.fill(change, 0, 4 * n, 0);
        Arrays.fill(update, 0, 4 * n, false);
    }

    /*
     * 将rt下标对应的节点进行更新，就是加上左右结点的值
     */
    private static void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    /**
     * 每次下发的任务当上面吼不住的时候，上面会先将任务下发，再递归地向下传递任务
     * 其中下发的任务有更新操作，也有增加操作
     * 其中在更新操作之后，所有的lazy清零，后面地添加操作才回使得lazy增加
     * 因此需要先对update进行判断，在对lazy进行判断
     * 
     * @param rt
     * @param ln
     * @param rn
     */
    private static void pushDown(int rt, int ln, int rn) {
        if (!update[rt]) {
            // 说明上面有更新任务下发

            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            update[rt] = false;

        }
        if (lazy[rt] != 0) {
            // 说明上面有add任务下发

            lazy[rt << 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * ln;
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1 | 1] += lazy[rt] * rn;

            lazy[rt] = 0;
        }
    }

    /**
     * 在开始时利用arr数组将sum数组填好
     * 
     * @param l
     * @param r
     * @param rt
     */
    public static void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = l + ((r - l) >> 1);
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }

    /**
     * 将区间[L,R]位置中的所有数字加上C
     * 
     * @param L
     * @param R
     * @param C
     * @param l
     * @param r
     * @param rt
     */
    public static void add(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && R >= r) {
            // 需要修改的范围包含住了当前范围的时候，就可以直接懒住
            lazy[rt] += C;
            sum[rt] += (r - l + 1) * C;
            return;
        }
        // 没有懒住，需要先将之前的任务下发
        int mid = l + ((r - l) >> 1);
        pushDown(rt, mid - l + 1, r - mid);
        // 然后进行递归
        if (L <= mid)
            add(L, R, C, l, mid, rt << 1);
        if (R > mid)
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        // 回溯，需要对rt位置的值重新进行更改
        pushUp(rt);
    }

    /**
     * 将区间[L,R]位置中的所有元素修改为C
     * 
     * @param L
     * @param R
     * @param C
     * @param l
     * @param r
     * @param rt
     */
    public static void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && R >= r) {
            // 区间包住，直接懒住
            update[rt] = true;
            lazy[rt] = 0;
            change[rt] = C;
            sum[rt] = (r - l + 1) * C;
            return;
        }
        int mid = l + ((r - l) >> 1);
        // 先对之前的任务进行下发
        pushDown(rt, mid - l + 1, r - mid);
        // 根据范围进行递归
        if (L <= mid)
            update(L, R, C, l, mid, rt << 1);
        if (R > mid)
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        // 回溯汇总
        pushUp(rt);
    }

    /**
     * 查询方法，请返回区间[L,R]上的所有数字的累加值和是多少
     * 
     * @param L
     * @param R
     * @param l
     * @param r
     * @param rt
     * @return
     */
    public static int query(int L, int R, int l, int r, int rt) {
        if (L <= l && R >= r) {
            return sum[rt];
        }

        int ans = 0;
        int mid = l + ((r - l) >> 1);
        // 先下发任务
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid)
            ans += query(L, R, l, mid, rt << 1);
        if (R > mid)
            ans += query(L, R, mid + 1, r, rt << 1 | 1);
        return ans;
    }

}
