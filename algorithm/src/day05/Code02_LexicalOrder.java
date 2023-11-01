package day05;

import java.util.ArrayList;
import java.util.List;

public class Code02_LexicalOrder {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        //就是使用递归呗，其实有时候我们不一定能够看出来需要使用递归
        for(int i = 1;i <= 9;i++) dfs(ans, n, i);
        return ans;
    }

    /**
     * 感觉自己太捞了，这一个题目使用的是字典树遍历，类似于多叉树遍历
     */
    public static void dfs(List<Integer> ans,int n,int num){
        if(num > n) return;
        ans.add(num);
        for(int i = 0;i < 10;i++){
            dfs(ans,n,num * 10 + i);
        }
    }
    
}
