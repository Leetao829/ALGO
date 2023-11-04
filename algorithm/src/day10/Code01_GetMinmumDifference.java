package day10;
/**
 * 获取二叉树中的最小绝对值之差
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 * 二叉搜索树呀，那差值最小值一定在相邻两个数之间
 * 
 */
public class Code01_GetMinmumDifference {
    public static int pre;
    public static int res;
    public int getMinimumDifference(TreeNode root) {
        res = Integer.MAX_VALUE;
        pre = -100000;//这个边界得处理好，防止出错
        dfs(root);
        return res;
    }
    public static void dfs(TreeNode node){
        if(node == null) return;
        dfs(node.left);
        res = Math.min(res, Math.abs(node.val-pre));
        pre = node.val;
        dfs(node.right);
    }
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
