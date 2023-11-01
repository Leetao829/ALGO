package day06;

/**
 * 寻找二叉树中的第二小的数值，其中每个节点要么没有孩子，要么是最有孩子的较小值
 * https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree/description/
 */
public class Code05_FindSecondMinNum {
    // 根节点一定是最小值
    // 对树进行遍历，找到大于严格root.val的最小值
    public static int ans;
    public int findSecondMinimumValue(TreeNode root) {
        int num = root.val;
        ans = -1;
        dfs(root, num);
        return ans;

    }
    public static void dfs(TreeNode node,int rv){
        if(node == null) return;
        //ans！=-1说明已经赋值完毕，node.val > ans 说明以node为根的整颗子树的值都大于ans，直接返回
        if(ans != -1 && node.val > ans) return;
        if(node.val > rv) ans = node.val;
        dfs(node.left, rv);
        dfs(node.right, rv);

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
