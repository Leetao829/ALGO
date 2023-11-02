package day08;
//https://leetcode.cn/problems/path-sum-iii/description/
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

public class Code06_PathSum {
    //最简单的思路，遍历所有的节点，求出以当前节点为根节点的所有满足条件的值，最后将所有的情况累加
    
    public static int res;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        res = 0;
        dfs(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;

    }

    public static void dfs(TreeNode node,long targetSum){
        if(node == null) return;
        if(node.val == targetSum) {
            res++;
        }
        dfs(node.left, targetSum-node.val);
        dfs(node.right, targetSum-node.val);

    }
}
