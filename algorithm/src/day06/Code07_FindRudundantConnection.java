package day06;

public class Code07_FindRudundantConnection {
    //这一题使用并查集还是好写一些的。
    public static int MAXN = 1001;
    public static int[] father = new int[MAXN];
    public static int size;
    public static void build(int n){
        for(int i  =1;i <= n;i++) father[i] = i;
        size = n;
    }
    public static int find(int x){
        if(x != father[x]) father[x] = find(father[x]);
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
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        build(n);
        for(int[] edge : edges){
            if(!union(edge[0], edge[1])) return edge;
        }
        return null;
    }

    
}
