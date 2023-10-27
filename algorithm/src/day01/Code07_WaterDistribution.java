import java.util.Arrays;

public class Code07_WaterDistribution {

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes){
        int ans = 0;
        //需要创建边的信息
        build(n);
        for(int i = 0;i < n;i++,cnt++){
            edges[cnt][0] = 0;
            edges[cnt][1] = i+1;
            edges[cnt][2] = wells[i];
        }
        for(int i = 0;i < pipes.length;i++,cnt++){
            edges[cnt][0] = pipes[i][0];
            edges[cnt][1] = pipes[i][1];
            edges[cnt][2] = pipes[i][2];
        }
        Arrays.sort(edges,0,cnt,(a,b)->a[2]-b[2]);
        for(int[] edge : edges){
            if(union(edge[0],edge[1])) ans += edge[2];
        }
        return ans;
    }


    //经典的供水问题，使用最小生成树
    public static int MAXN = 10010;
    public static int[] father = new int[MAXN];
    //这里假设了有一个虚的水源，因此边会增加
    public static int[][] edges = new int[MAXN << 1][3];
    public static int cnt;
    public static void build(int n){
        cnt = 0;
        for(int i = 0;i <= n;i++) father[i] = i;
    }
    public static int find(int x){
        if(father[x] != x){
            father[x] = find(father[x]);
        }
        return father[x];
    }
    public static boolean union(int x,int y){
        int fx = find(x);
        int fy = find(y);
        if(fx != fy){
            father[fx] = fy;
            return true;
        }
        return false;
    }
    
}
