//使用链式前向星完成拓扑排序

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code01_TopoSortNowStatic {

    //先写数据结构
    public static int MAXN = 200001;
    //头节点指向的是连接的第一条边
    public static int[] head = new int[MAXN];
    //下标代表每一条边
    public static int[] next = new int[MAXN];
    //代表边指向的节点
    public static int[] to = new int[MAXN];
    public static int[] indegree = new int[MAXN];
    public static int[] queue = new int[MAXN];
    public static int[] ans = new int[MAXN];
    public static int cnt;
    public static int l,r;
    public static int m,n;

    //初始化的方法
    public static void build(int n){
        cnt = 1;
        Arrays.fill(head, 0,n+1,0);
        Arrays.fill(indegree, 0,n+1,0);
    }

    //添加边的方法，这个很关键
    public static void addEdge(int f,int t){
        next[cnt] = head[f];
        to[cnt] = t;
        head[f] = cnt++;
    }

    public static boolean topoSort(){
        l = r = 0;
        int fill = 0;
        for(int i = 1;i <= n;i++){
            if(indegree[i] == 0){
                //ans[fill++] = i;
                queue[r++] = i;
            }
        }
        while (l < r) {
            int cur = queue[l++];
            ans[fill++] = cur;
            for(int e = head[cur];e != 0;e = next[e]){
                if(--indegree[to[e]] == 0){
                  //  ans[fill++] = to[e];
                    queue[r++] = to[e];
                }
            }
        }
        return fill == n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int)in.nval;
            in.nextToken();
            m = (int)in.nval;
            build(n);
            for(int i = 0,from,to;i < m;i++){
                in.nextToken();
                from = (int)in.nval;
                in.nextToken();
                to = (int)in.nval;
                addEdge(from, to);
                indegree[to]++;
            }
            if (!topoSort()) {
				out.println(-1);
			} else {
				for (int i = 0; i < n - 1; i++) {
					out.print(ans[i] + " ");
				}
				out.println(ans[n - 1]);
			}
        }
        out.flush();
        out.close();
        br.close();
    }


}