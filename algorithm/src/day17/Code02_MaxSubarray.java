package day17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code02_MaxSubarray {
    public static int MAXN = 1001;
    public static int[] arr = new int[MAXN];
    public static int n;
    //使用三个变量存储结果
    public static int left,right,sum;//只有当pre大于sum时才会更新这三个变量
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for(int i = 0;i < n;i++){
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            sum = Integer.MIN_VALUE;
            left = right = -1;
            int l = 0,r = 0,pre = Integer.MIN_VALUE;
            for(;r < n;r++){
                if(pre >= 0){
                    pre += arr[r];
                }else{
                    l = r;
                    pre = arr[r];
                }
                if(pre > sum){
                    sum = pre;
                    left = l;
                    right = r;

                }
            }
            System.out.println("["+left+","+right+"]"+":"+sum);


        }
        out.flush();
        out.close();
        br.close();
    }
    
}
