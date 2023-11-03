package day09;
/**
 * 寻找树中最底层最左边的节点
 * https://leetcode.cn/problems/find-bottom-left-tree-value/
 */
public class Code04_FindBottomLeftVal {
    public static TreeNode[] queue = new TreeNode[10000];
    public static int l,r;
    public static int res;
    public int findBottomLeftValue(TreeNode root) {
        l = r = 0;
        queue[r++] = root;
        TreeNode node = null;
        while (l < r) {
            node = queue[l++];
            if(node.right != null) queue[r++] = node.right;
            if(node.left != null) queue[r++] = node.left;
            
        }
        return node.val;
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
