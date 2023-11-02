package day08;
//https://leetcode.cn/problems/longest-univalue-path/
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
public class Code03_LongestUnivaluePath {
    //类似于树状dp的一道题，对于每个节点定义左最长路径与右最长路径，对于每个节点，取两者之和即可
    public static int res;
    public int longestUnivaluePath(TreeNode root) {
        int[] ans = dfs(root);
        res  = Math.max(res, ans[0]+ans[1]);
        return res;
    }

    public static int[] dfs(TreeNode node){
        if(node == null) return new int[]{0,0};
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int l = 0;
        int r = 0;
        if(node.left != null) {
            if(node.val == node.left.val) l = 1 + Math.max(left[0], left[1]);
        }
        if(node.right != null){
            if(node.val == node.right.val) r = 1 + Math.max(right[0], right[1]);
        }
        res = Math.max(res, l+r);
        return new int[]{l,r};
        

    }
}

 