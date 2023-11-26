package day28;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树中的节点累加和，
 * https://leetcode.cn/problems/path-sum-iii/
 * 这一题比较特殊的一点是：枚举到达每一个节点时的前缀之和
 */
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
public class Code05_PathSum {
    public static int ans;

    public int pathSum(TreeNode root, int targetSum) {
        ans = 0;
        Map<Long,Integer> preSum = new HashMap<>();
        preSum.put(0L, 1);
        dfs(root, targetSum, 0, preSum);
        return ans;

    }

    public static void dfs(TreeNode node,int targetSum,long sum,Map<Long,Integer> preSum){
        if(node == null) return;
        sum += node.val;
        //没来到一个节点就需要进行统计
        ans += preSum.getOrDefault(sum-targetSum, 0);
        preSum.put(sum,preSum.getOrDefault(sum, 0)+1);
        dfs(node.left, targetSum, sum, preSum);
        dfs(node.right, targetSum, sum, preSum);
        preSum.put(sum, preSum.get(sum)-1);
        
    }

}
