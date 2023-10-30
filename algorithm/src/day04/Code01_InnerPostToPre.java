package day04;

import java.util.Scanner;

public class Code01_InnerPostToPre {
    public static StringBuilder builder = new StringBuilder();
    public static void main(String[] args) {
        builder.setLength(0);
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        dfs(a1, 0, a1.length-1, a2, 0, a2.length-1);
        System.out.println(builder.toString());
    }
    public static void dfs(char[] s1 ,int i1,int i2,char[] s2,int p1,int p2){
        if(i1 > i2 || p1 > p2) return;
        char ch = s2[p2];
        builder.append(ch);
        int index;
        for(index = i1;index <= i2;index++){
            if(s1[index] == ch) break;
        }
        dfs(s1, i1, index-1, s2, p1,p1+index-1-i1);
        dfs(s1, index+1, i2, s2, p2-i2+index,p2-1);
    }
}
