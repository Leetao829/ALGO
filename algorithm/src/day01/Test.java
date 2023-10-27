import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    //建立拓扑排序的数据结构
    public static int MAXN = 200001;
    public static int[] queue = new int[MAXN];
    public static int l,r;
    public static int[] indegree = new int[MAXN];
    public static  int m,n;
    public static int[] ans = new int[MAXN];

    //再来熟悉一下比赛当中输入输出的写法，以拓扑排序为例
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while(k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int)in.nval;//结点的个数
            in.nextToken();
            m = (int) in.nval;//边的个数
            Arrays.fill(indegree,0,n+1,0);
            //动态建图
            List<List<Integer>> graph = new ArrayList<>();
            for(int i = 0;i <= n;i++) graph.add(new ArrayList<>());
            for(int i = 0,from,to;i < m;i++){
                in.nextToken();
                from = (int)in.nval;
                in.nextToken();
                to = (int)in.nval;
                graph.get(from).add(to);
                indegree[to]++;
            }
            boolean flag = topoSort(graph);
            if(!flag) out.println(-1);
            else {
                for(int i = 0;i < n-1;i++){
                    out.print(ans[i] + " ");;
                }
                out.println(ans[n-1]);
            }

        }


        out.flush();
        out.close();
        br.close();
    }

    public static boolean topoSort(List<List<Integer>> graph){
        l = r = 0;
        int index = 0;
        for(int i = 1;i <= n;i++){
            if(indegree[i] == 0){
                ans[index++] = i;
                queue[r++] = i;
            }
        }
        while(l < r){
            int cur = queue[l++];
            for(int next : graph.get(cur)){
                if(--indegree[next] == 0){
                    ans[index++] = next;
                    queue[r++] = next;
                }
            }
        }
        return index == n ? true : false;
    }
    
}
