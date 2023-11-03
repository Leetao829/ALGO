package day09;
/**
 * 在二叉树上分配硬币，我们只需要维护以每个节点为根节点的节点数和高度信息就能做出最优的判断
 * 有点像树形dp的意思，补过在dp的返回值之前我们需要统计相关信息并做出决策
 * https://leetcode.cn/problems/distribute-coins-in-binary-tree/
 */
public class Code06_DistributeCoins {
    public static int res;
    public int distributeCoins(TreeNode root) {
        res = 0;
        dfs(root);
        
        return res;
    }

    public static int[] dfs(TreeNode node){
        if(node == null) return new int[]{0,0};
        int[] leftData = dfs(node.left);
        int[] rightData = dfs(node.right);
        int leftNum = leftData[0];
        int leftCoin = leftData[1];
        int rightNum = rightData[0];
        int rightCoin = rightData[1];
        res += (Math.abs(leftCoin-leftNum) + Math.abs(rightCoin-rightNum));
        return new int[]{leftNum+rightNum+1,leftCoin+rightCoin+node.val};
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