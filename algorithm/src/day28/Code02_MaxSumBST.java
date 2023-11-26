package day28;

/**
 * 二叉搜索子树中的最大键值和，使用树形dp
 * 树形dp:
 * 1.和dp一样找到枚举点，这种枚举点相当与分类讨论，并且这种枚举点，能够利用子问题解决原问题
 * 2.根据枚举点，找到需要子树状态（子状态）的信息
 * 3.根据信息全集设定返回值，原问题可能只需要其中一种信息
 * https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/
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

// 需要的树的状态信息
class Info {
    int max;
    int min;
    boolean isBst;
    int sum;
    int bSum;

    public Info(int max, int min, boolean isBst, int sum,int bSum) {
        this.max = max;
        this.min = min;
        this.isBst = isBst;
        this.sum = sum;//这个是结点的累加和
        this.bSum = bSum;//这个是题目中要求的bst的累加和
    }

}

public class Code02_MaxSumBST {
    public int maxSumBST(TreeNode root) {

        return dfs(root).bSum;
    }

    public static Info dfs(TreeNode node) {
        if (node == null) {
            //空树的设定需要根据题目的具体意思以及数据量决定
            return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0,0);
        }
        Info leftInfo = dfs(node.left);
        Info rightInfo = dfs(node.right);
        int max = Math.max(node.val, Math.max(leftInfo.max, rightInfo.max));
        int min = Math.min(node.val, Math.min(leftInfo.min, rightInfo.min));
        boolean isBst = leftInfo.isBst && rightInfo.isBst && node.val > leftInfo.max && node.val < rightInfo.min;
        int sum = leftInfo.sum + rightInfo.sum + node.val;
        //不要node节点
        int bSum = Integer.max(leftInfo.bSum, rightInfo.bSum);
        //要Node节点，需要是搜索树
        if(isBst){
            bSum = Math.max(bSum, sum);
        }
        return new Info(max, min, isBst, sum,bSum);
    }

}
