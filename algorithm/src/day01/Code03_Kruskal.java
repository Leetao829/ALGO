import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code03_Kruskal {
    //来写一下最小生成树的模板代码，使用洛谷的
    public static int MAXN = 5002;
    public static int MAXM = 200002;
    //使用并查集判断是否形成环
    public static int[] father = new int[MAXN];
    public static int[][] edges = new int[MAXM][3];

    public static int m,n;

    public static int find(int x){
        if(x != father[x]){
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
    public static void build(){
        for(int i = 1;i <= n;i++){
            father[i] = i;
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        int k = 0;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for(int i = 0;i < m;i++){
                in.nextToken();
                edges[i][0] = (int)in.nval;
                in.nextToken();
                edges[i][1] = (int)in.nval;
                in.nextToken();
                edges[i][2] = (int)in.nval;
            }
            //先对数组按照边的权重从小到大进行排序
            Arrays.sort(edges,0,m,(a,b)->a[2]-b[2]);
            int ans = 0;
            int edgeCnt = 0;//用于统计边的条数
            for(int[] edge : edges){
                if(union(edge[0], edge[1])){
                    ans += edge[2];
                    edgeCnt++;
                }
            }
            out.println(edgeCnt == n-1 ? ans : "orz");

        }

        out.flush();
        out.close();
        br.close();
    }
    
}
