package day06;

public class Code02_FindCIrcleNum {
    //这个题感觉使用并查集也能够解决，先使用并查集试试
    // public static int MAXN = 201;
    // public static int[] father = new int[MAXN];
    // public static int size;
    // public static void build(int n){
    //     size  =n;
    //     for(int i = 0;i < n;i++) father[i] = i;
    // }
    // public static int find(int x){
    //     if(x != father[x]) father[x] = find(father[x]);
    //     return father[x];
    // }
    // public static void union(int x,int y){
    //     int fx = find(x);
    //     int fy = find(y);
    //     if(fx != fy){
    //         size--;
    //         union(fx, fy);
    //     }
    // }
    // public int findCircleNum(int[][] isConnected) {
    //     int n = isConnected.length;
    //     build(n);
    //     for(int i = 0;i < n;i++){
    //         for(int j = i;j < n;j++){
    //             if(isConnected[i][j] == 1) union(i, j);
    //         }
    //     }
    //     return size;
    // }

    //上面使用并查集没有问题，还是写一些深搜吧，得提升提升自己的coding能力了
    public static int provinces;
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        provinces = 0;
        boolean[] vis = new boolean[n];
        for(int i = 0;i < n;i++){
            if(!vis[i]){
                provinces++;
                dfs(isConnected, vis, i);
            }
        }
        return provinces;

    }
    public static void dfs(int[][] isConnected,boolean[] vis, int x){
        if(vis[x]) return;
        vis[x] = true;
        for(int i = 0;i < isConnected.length;i++){
            if(isConnected[x][i] == 1){
                dfs(isConnected,vis,i);
            }
        }
    }
}
