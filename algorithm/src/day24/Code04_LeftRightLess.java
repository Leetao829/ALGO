package day24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/*
 * 单调栈模板题，在一个数组中找到位置i上左右两边比i位置上数字小的最近的位置，数组中可能存在重复的数字
 * https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
 */
public class Code04_LeftRightLess {
    public static int MAXN = 1000001;
    public static int[] arr = new int[MAXN];
    public static int[] stack = new int[MAXN];
    public static int[][] res = new int[MAXN][2];
    public static int n,r;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for(int i = 0;i < n;i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            computer();
            for(int i = 0;i < n;i++){
                out.println(res[i][0] + " "+res[i][1]);
            }
            
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void computer(){
        r = 0;//栈清空，栈中存储的是下标
        for(int i = 0;i < n;i++){
            while(r > 0 && arr[stack[r-1]] >= arr[i]){
                res[stack[--r]][0] = r > 0 ? stack[r-1] : -1;
                res[stack[r]][1] = i;
                
            }
            stack[r++] = i;
        }
        //进入清算阶段
        while (r > 0) {
            res[stack[--r]][0] = r > 0 ? stack[r-1] : -1;
            res[stack[r]][1] = -1;
        }
        //进入调整阶段，n-1位置的右面的结果下表一定是-1
        for(int i = n-2;i >= 0;i--){
            if(res[i][1] != -1 && arr[i] == arr[res[i][1]]){
                res[i][1] = res[res[i][1]][1];
            }
        }

    }
}
