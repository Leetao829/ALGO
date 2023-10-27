import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code05_FodeLines {
    //使用拓扑排序实现统计食物链的个数，其中需要我们在遍历的过程中向下推送消息
    public static int MAXN = 5001;
    public static int MAXM = 500001;
    public static int MOD = 80112002;
    //使用链式前向星创建拓扑排序
    public static int[] head = new int[MAXN];
    public static int[] next = new int[MAXM];
    public static int[] to = new int[MAXM];
    public static int[] indgree = new int[MAXN];
    //用于统计前面推送的信息
    public static int[] lines = new int[MAXN];
    public static int[] queue = new int[MAXN];
    public static int l,r;
    public static int cnt;
    public static int m,n;

    public static void build(int n){
        cnt = 1;
        l = r = 0;
        Arrays.fill(head, 0,n+1,0);
        Arrays.fill(indgree,0,n+1,0);
        Arrays.fill(lines, 0,n+1,0);
    }

    public static void addEdge(int f,int t){
        next[cnt] = head[f];
        to[cnt] = t;
        head[f] = cnt++;
    }

    public static int ways(){
        int ans = 0;
        for(int i = 1;i <= n;i++){
            if(indgree[i] == 0){
                queue[r++] = i;
                lines[i] = 1;
            }
        }
        while(l < r){
            int cur = queue[l++];
            if(head[cur] == 0){
                //表示该节点没有前驱节点，并且没有后继节点
                ans = (ans + lines[cur]) % MOD;
            }else{
                for(int e = head[cur],v;e != 0;e = next[e]){
                    v = to[e];
                    lines[v] = (lines[cur] + lines[v]) % MOD;
                    if(--indgree[v] == 0){
                        queue[r++] = v;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while(k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int)in.nval;
            build(n);
            for(int i = 0;i < m;i++){
                in.nextToken();
                int f = (int) in.nval;
                in.nextToken();
                int t = (int)in.nval;
                addEdge(f, t);
                indgree[t]++;
            }
            out.println(ways());
        }
        out.flush();
        out.close();
        br.close();
    }
}
