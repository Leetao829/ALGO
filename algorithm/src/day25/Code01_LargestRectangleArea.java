package day25;
/**
 * 柱状图中最大矩形面积：单调栈，技巧：遇到相等的时候直接进行结算
 * 相当与用当前相等的位置代替原来的位置，后面可以修正正确
 */
public class Code01_LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int n = heights.length;
        int[] stack = new int[n];
        int r = 0;
        for(int i = 0;i < n;i++){
            while (r > 0 && heights[stack[r-1]] >= heights[i]) {
                int cur = heights[stack[--r]];
                if(r > 0) res = Math.max(res, cur * (i - 1 - stack[r-1]));
                else res = Math.max(res, cur * i);
            }
            stack[r++] = i;
        }
        while (r > 0) {
            int cur = heights[stack[--r]];
            if(r > 0) res = Math.max(res, cur * (n - 1 - stack[r - 1]));
            else res = Math.max(res, cur * n);   
        }
        return res;
    }
}
