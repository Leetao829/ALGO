package day28;
/**
 * 监控摄像头
 * https://leetcode.cn/problems/binary-tree-cameras/
 * 将树根据所有被监控的情况进行状态划分，一功能划分三种情况
 * 根据子树的这三种情况，一定能够决定出当前节点的状态情况
 * 其中用到了贪心（根据子状态和题目要求：需要的监控最少）
 * 
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
public class Code04_MinCameraCover {
    public static int ans;
    public int minCameraCover(TreeNode root) {
        ans = 0;
        int res = dfs(root);
        if(res == 0) ans++;
        return ans;

    }
    public static int dfs(TreeNode node){
        if(node == null) return 1;//空树是被监控但是节点上没有放监控
        int leftInfo = dfs(node.left);
        int rightInfo = dfs(node.right);
        if(leftInfo == 0 || rightInfo == 0){
            //当前节点必须放监控，并且状态变成2
            ans++;
            return 2;
        }
        if(leftInfo == 2 || rightInfo == 2){
            //这时当前节点一定被子节点监控到，并且不需要放监控
            return 1;
        }
        //这时，孩子节点一定被监控到，但是由于孩子没有监控，当前节点不能被监控到
        //为了使监控数量最少，将放监控的位置交给父节点
        return 0;
    }
    
}
