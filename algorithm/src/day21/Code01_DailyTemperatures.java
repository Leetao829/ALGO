package day21;
/**
 * 每日温度：单调栈，题意就是寻找当前位置上右边大于当前位置值得最近位置，只需要维护从大到小的单调栈即可
 * https://leetcode.cn/problems/daily-temperatures/
 */
public class Code01_DailyTemperatures {

    //使用自己维护的栈
    public static int MAXN = 100001;
    public static int[] stack = new int[MAXN];
    public static int r;
    
    public int[] dailyTemperatures(int[] temperatures) {
        r = 0;
        int n = temperatures.length;
        int[] res = new int[n];
        for(int i = 0;i < n;i++){
            while(r > 0 && temperatures[i] > temperatures[stack[r-1]]){
                res[stack[--r]] = i - stack[r];
            }
            stack[r++] = i;
        }
        return res;
    }
    
}
