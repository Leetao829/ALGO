package day28;
/**
 * 二叉树的直径：还是使用树形dp
 * https://leetcode.cn/problems/diameter-of-binary-tree/
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


class Info{
    int height;//高度，在要根节点这种枚举时，是需要左右子树的高度信息的
    int diameter;
    public Info(int height,int diameter){
        this.height = height;
        this.diameter = diameter;
    }
}
public class Code03_DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root){

        return dfs(root).diameter;
    }
    public static Info dfs(TreeNode node){
        if(node == null) return new Info(0, 0);
        Info leftInfo = dfs(node.left);
        Info rightInfo = dfs(node.right);
        int height = 1 + Math.max(leftInfo.height, rightInfo.height);
        int diameter = leftInfo.height + rightInfo.height;
        diameter = Math.max(diameter, Math.max(leftInfo.diameter, rightInfo.diameter));
        return new Info(height, diameter);
    }
}
