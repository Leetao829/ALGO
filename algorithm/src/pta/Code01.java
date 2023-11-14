package pta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
/**
 * 最短工期代码，感觉这一题是使用拓扑排序实现的，但是中间得记录一些中间信息
 */
public class Code01 {
    public static int MAXN = 101;
    public static int MAXM = 10001;
    //使用链式前向星建图
    public static int[] head = new int[MAXN];
    public static int[] next = new int[MAXM];
    public static int[] to = new int[MAXM];
    public static int[] weight = new int[MAXM];
    public static int[] indegree = new int[MAXN];
    public static int[] path = new int[MAXN];
    public static int[] queue = new int[MAXN];
    public static int l,r,res;
    public static int n,m;
    public static int cnt;
    public static void build(){
        cnt = 1;
        Arrays.fill(head, 0,n,-1);//先将head初始化成为-1
        Arrays.fill(indegree, 0,n,0);
        Arrays.fill(path, 0,n,0);
    }
    public static void addEdge(int f ,int t,int v){
        next[cnt] = head[f];
        to[cnt] = t;
        weight[cnt] = v;
        head[f] = cnt++;
    }

    public static boolean topo(){
        l = r = res = 0;
        int fill = 0;
        for(int i = 0;i < n;i++){
            //先将入度为零的节点入队列
            if(indegree[i] == 0) queue[r++] = i;
        }
        while (l < r) {
            int cur = queue[l++];
            fill++;
            res = Math.max(res, path[cur]);
            for(int e = head[cur],t;e != -1;e = next[e]){
                t = to[e];
                if(path[cur] + weight[e] > path[t]) path[t] = path[cur] + weight[e];
                if(--indegree[t] == 0) queue[r++] = t;
            }
            
        }
        return fill == n;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while(k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for(int i = 0,f,t,v;i < m;i++){
                in.nextToken();
                f = (int) in.nval;
                in.nextToken();
                t = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                addEdge(f, t,v);
                indegree[t]++;
            }
            
            if(!topo()) {
                out.println("Impossible");
            }else{
                out.println(res);
            }
        }
        out.flush();
        out.close();
        br.close();
    }
    
}
