import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code01_TopoSortNowCoder {

    //实现拓扑排序：首先确定最大节点和边的数量
    public static int MAXN = 200001;
    //使用到队列
    public static int[] queue = new int[MAXN];
    public static int l,r;
    //拓扑排序入度表
    public static int[] indgree = new int[MAXN];
    //收集结果的答案
    public static int[] ans = new int[MAXN];
    public static int n,m;
    //完成基本的拓扑排序，使用输入输效率较高的写法
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            //读取整个文件才会结束
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            //使用动态建图，后面会使用链式前向星建图
            List<List<Integer>> graph = new ArrayList<>();
            for(int i = 0;i <= n;i++){
                graph.add(new ArrayList<>());
            }
            //将入度初始化为零
            Arrays.fill(indgree,0,n+1,0);
            //开始读入数据
            for(int i = 0,from,to;i < m;i++){
                in.nextToken();
                from = (int) in.nval;
                in.nextToken();
                to = (int) in.nval;
                graph.get(from).add(to);
                indgree[to]++;
            }
            if(!topoSort(graph)) out.println(-1);
            else{
                for(int i = 0;i < n-1;i++) out.print(ans[i] + " ");
                out.println(ans[n-1]);
            }

        }
        //最后将内存中的数据一起刷入到文件中
        out.flush();
        out.close();
        br.close();
    }

    //拓扑排序，能够拓扑排序返回true，不能够拓扑排序就返回false
    public static boolean topoSort(List<List<Integer>> graph){
        l = r = 0;
        for(int i = 1;i <= n;i++){
            if(indgree[i] == 0) queue[r++] = i;
        }
        int fill = 0;;
        while(l < r){
            int cur = queue[l++];
            ans[fill++] = cur;
            for(int next : graph.get(cur)){
                if(--indgree[next] == 0){
                    queue[r++] = next;
                }
            }
        }
        return fill == n;
    }

    
}
