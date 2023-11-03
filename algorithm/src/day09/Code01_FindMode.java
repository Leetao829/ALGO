package day09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在二叉搜索树中寻找众数
 * https://leetcode.cn/problems/find-mode-in-binary-search-tree/
 */
public class Code01_FindMode {
    //整体思路没有问题，但是没有想到自己的coding能力这么差，中间考虑的有点多了
    public static int count,maxCount,cur;
    public static List<Integer> ans = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        count = maxCount = 0;
        cur = Integer.MAX_VALUE;
        ans.clear();
        dfs(root);
        int[] res = new int[ans.size()];
        int i = 0;
        for(int num : ans) res[i++] = num;
        return res;
    }

    public static void dfs(TreeNode node){
        if(node == null) return;
        dfs(node.left);
        update(node.val);
        dfs(node.right);
    }
    public static void update(int x){
        if(x == cur){
            count++;
        }
        else{
            cur = x;
            count = 1;
        }
        if(count == maxCount){
            ans.add(x);
        }
        if(count > maxCount){
            maxCount = count;
            ans.clear();
            ans.add(x);
        }
    }

    
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
