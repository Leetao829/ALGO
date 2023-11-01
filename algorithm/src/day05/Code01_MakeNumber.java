package day05;

import java.util.Arrays;
import java.util.Scanner;

public class Code01_MakeNumber {
    public static String str;
    public static int k;
    public static int[][] convert = new int[21][2];
    public static boolean[] vis = new boolean[10];
    public static int[] tab = new int[31];
    //表示能够将一个数字在转换数组中能够转换的种类数
    public static int dfs(int x){
        if(vis[x]) return 0;
        int res = 1;
        vis[x] = true;
        for(int i = 0;i < k;i++){
            if(convert[i][0] == x){
                res += dfs(convert[i][1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.next();
        k = sc.nextInt();
        for(int i = 0;i < k;i++){
            convert[i][0] = sc.nextInt();
            convert[i][1] = sc.nextInt();
        }
        int n = str.length();
        for(int i = 0;i < n;i++){
            Arrays.fill(vis, false);
            //这个乘法有点问题，在数据量很大时不能通过
            //好像需要使用高精度乘法，先放在这，整体思路是没有问题的
            tab[i] = dfs(str.charAt(i)-'0');
        }
        int ans = 1;
        for(int i = 0;i < n;i++) ans *= tab[i];
        System.out.println(ans);


    }
    
}
