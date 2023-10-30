package day04;
//洛谷这一题竟然不需要进行去重
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Code02_SelectNum {
    public static int[] arr = new int[21];
    public static int n,m,res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        res = 0;
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0;i < n;i++) arr[i] = sc.nextInt();
        Arrays.sort(arr,0,n);
        dfs(0, m, 0);
        System.out.println(res);
       // for(int i = 2;i <= 10;i++) System.out.println(judge(i));

    }
    public static void dfs(int sum,int k,int i){
        if(k == 0){
            if(judge(sum)) res += 1;
            return;
        }
        if(i >= n) return;
       // Set<Integer> set = new HashSet<>();
        for(int j = i;j < n;j++){
          //  if(!set.contains(arr[j])){
                sum += arr[j];
              //  set.add(arr[j]);
                dfs(sum, k-1, j+1);
                sum-=arr[j];
         //   }
        }
        
    }
    public static boolean judge(int x){
        boolean flag;
        int i=0;
        int j=0;
        flag=true;
        for(j=2;j<=Math.sqrt(x);j++){
            if(x%j==0){
                flag=false;
                break;
            }
        }
        if(j>Math.sqrt(x)){
            return true;
        }else{
            return false;
        }
    }
    
}
