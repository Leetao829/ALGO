package day25;
/**
 * 滑动窗口最大值，单调队列经典用法
 * https://leetcode.cn/problems/sliding-window-maximum/
 */
public class Code03_MaxSlidingWindows {
    public static int MAXN = 100001;
    public static int[] dequeue = new int[MAXN];
    public static int h,t;
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        h = t = 0;
        //先将k-1个数字放进单调队列中，单调队列中存放的是数组下标
        for(int i = 0;i < k-1;i++){
            while (h < t && nums[dequeue[t - 1]] <= nums[i]) t--;
            dequeue[t++] = i;
        }
        int m = n - k + 1;
        int[] res = new int[m];
        for(int r = k - 1;r < n;r++){
            while (h < t && nums[dequeue[t - 1]] <= nums[r]) {
                t--; 
            }
            dequeue[t++] = r;
            res[r-k+1] = nums[dequeue[h]];
            if(dequeue[h] == r - k + 1) h++;
        }
        return res;
    }

    
}