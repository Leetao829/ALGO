package day25;
/**
 * 给定一个数组，要求找到一个最长的子数组，子数组中的最大值与最小值之差小于等于给定值
 * 使用单调队列，其中这一题需要分析单调性，决定何时加入窗口以及何时弹出窗口
 * 由于窗口的大小并不是固定的，因此需要仔细分析
 */
public class Code04_LongestSubarray {
    public static int MAXN = 100001;
    public static int[] maxDeque = new int[MAXN];
    public static int[] minDeque = new int[MAXN];
    public static int maxH,maxT,minH,minT;
    public static int[] arr;
    public int longestSubarray(int[] nums, int limit) {
        maxH = maxT = minH = minT = 0;
        arr = nums;
        int n = arr.length;
        int ans = 0;
        //枚举每一个坐标位置能够享有延伸的最大长度
        for(int l = 0,r = 0;l < n;l++){
            while (r < n && ok(r,limit)) {
                r++;
            }
            //退出循环说明当前l位置的所能够延伸的最远距离已经求出
            ans = Math.max(ans, r - l);
            //在l++之前需要将两个队列中过期的下标清除
            clear(l,r);
        }

        return ans;
    }
    private static boolean ok(int r,int limit) {
        while (maxH < maxT && arr[maxDeque[maxT - 1]] <= arr[r] ) {
            maxT--;
        }
        maxDeque[maxT++] = r;
        while (minH < minT && arr[minDeque[minT - 1]] >= arr[r]) {
            minT--;
        }
        minDeque[minT++] = r;
        return arr[maxDeque[maxH]] - arr[minDeque[minH]] <= limit;
    }

    public static void clear(int l,int r){
        if(maxDeque[maxT - 1] - maxDeque[maxH] == r - l ) maxH++;
        if(minDeque[minT - 1] - minDeque[minH] == r - l) minH++;
    }
    
}
