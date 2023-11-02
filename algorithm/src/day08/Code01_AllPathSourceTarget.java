package day08;
//https://leetcode.cn/problems/all-paths-from-source-to-target/
import java.util.ArrayList;
import java.util.List;

public class Code01_AllPathSourceTarget {
    public static int[] path = new int[16];//使用数组存储中间的路径
    public static int n;
    //直接写递归
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        n = graph.length;
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, graph, 0, 0);
        return ans;

    }
    public static void dfs(List<List<Integer>> ans,int[][] graph,int i,int index){
        if(i == n-1){
            path[index++] = i;
            List<Integer> list = new ArrayList<>();
            for(int j = 0;j < index;j++) list.add(path[j]);
            ans.add(list);
            return;
        }
        path[index++] = i;
        for(int j : graph[i]){
            dfs(ans, graph, j, index);
        }
    }
    
}
