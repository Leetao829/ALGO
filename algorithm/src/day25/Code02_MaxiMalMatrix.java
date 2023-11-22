package day25;
/**
 * 最大矩形，前面柱状图面积的变形，使用单调栈
 * https://leetcode.cn/problems/PLYXKQ/
 */
public class Code02_MaxiMalMatrix {
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        if(n == 0) return 0;
        int m = matrix[0].length;
        int res = 0;
        int[] arr = new int[m];
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(matrix[i][j] == '0') arr[j] = 0;
                else arr[j] += 1;
            }
            res = Math.max(res, getMaxMal(arr));
        }
        return res;
    }

    public static int getMaxMal(int[] arr){
        int res = 0;
        int n = arr.length;
        int[] stack  = new int[n];
        int r = 0;
        for(int i = 0;i < n;i++){
            while(r > 0 && arr[stack[r-1]] >= arr[i]){
                int cur = arr[stack[--r]];
                if(r > 0) res = Math.max(res, cur * (i - 1 - stack[r-1]));
                else res = Math.max(res, cur * i);
            }
            stack[r++] = i;
        }
        while (r > 0) {
            int cur = arr[stack[--r]];
            if(r > 0) res = Math.max(res, cur * (n - 1 - stack[r-1]));
            else res = Math.max(res, cur * n);
            
        }
        return res;
    }    
}
