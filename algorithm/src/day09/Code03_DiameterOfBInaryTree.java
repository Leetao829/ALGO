package day09;
//二叉树中的直径，树形dp，对于每一个节点，维护左树路径最大长度与右树路径最大长度
//https://leetcode.cn/problems/diameter-of-binary-tree/description/

public class Code03_DiameterOfBInaryTree {
    public static int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        int[] data = dfs(root);
        ans = Math.max(ans, data[0] + data[1]);
        return ans;
    }

    public static int[] dfs(TreeNode node){
        if(node == null) return new int[]{0,0};
        int[] leftData = dfs(node.left);
        int[] rightData = dfs(node.right);
        int l = 0;
        int r = 0;
        if(node.left != null){
            l = 1 + Math.max(leftData[0], leftData[1]);
        }
        if(node.right != null){
            r = 1 + Math.max(rightData[0], rightData[1]);
        }
        ans = Math.max(ans, l + r);
        return new int[]{l,r};
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