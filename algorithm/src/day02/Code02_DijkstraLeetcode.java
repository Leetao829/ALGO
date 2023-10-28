package day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 写一下leetcode中的算法模板
 */
public class Code02_DijkstraLeetcode {
    //动态建图+普通堆的形式
    public static int networkDelayTime1(int[][] times, int n, int s){
        List<List<int[]>> graph = new ArrayList<>();
        //首先建图
        for(int i = 0;i <= n;i++) graph.add(new ArrayList<>());
        for(int[] edge : times){
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
        }
        int[] distances = new int[n+1];
        //初始化最大距离为无穷大
        Arrays.fill(distances, 1,n+1,Integer.MAX_VALUE);
        //visited[i] = true代表该节点上的最小距离已经确定，不需要再访问
        boolean[] visited = new boolean[n+1];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        distances[s] = 0;
        heap.add(new int[]{s,0});
        //开始广搜
        while (!heap.isEmpty()) {
            int u = heap.poll()[0];
            if(visited[u]) continue;
            visited[u] = true;
            for(int[] edge : graph.get(u)){
                int v = edge[0];
                int w = edge[1];
                if(!visited[v] && distances[u] + w < distances[v]){
                    distances[v] = distances[u] + w;
                    heap.add(new int[]{v,distances[u]+w});
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for(int i = 1;i <= n;i++){
            if(distances[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, distances[i]);
        }
        return res;
    }
    
}
