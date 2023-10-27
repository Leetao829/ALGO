import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code02_TopoSortLuogo {
    //使用小根堆求解出拓扑排序中的最小序
    public static int  MAXN = 100002;
    public static int MAXM = 100002;
    public static int[] head = new int[MAXN];
    public static int[] next = new int[MAXM];
    public static int[] to = new int[MAXM];
    //使用小根堆
    public static int[] heap = new int[MAXN];
    public static int[] ans = new int[MAXN];
    public static int[] indegree = new int[MAXN];
    public static int heapSize;
    public static int n,m;
    public static int cnt;
    public static void build(int n){
        Arrays.fill(head, 0,n+1,0);
        Arrays.fill(indegree,0,n+1,0);
        cnt = 1;
        heapSize = 0;
    }

    public static void addEdge(int f,int t){
        next[cnt] = head[f];
        to[cnt] = t;
        head[f] = cnt++;
    }

    public static void swap(int[] heap,int i,int j){
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    //向小根堆中加入节点
    public static void push(int index){
        int i = heapSize;
        heap[heapSize++] = index;
        while(heap[i] < heap[(i-1)/2]){
            swap(heap, (i-1)/2, i);
            i = (i-1)/2;
        }
    }

    //从小根堆中弹出节点
    public static int pop(){
        int res = heap[0];
        heap[0] = heap[--heapSize];
        int i = 0;
        int l = 1;
        while(l < heapSize){
            int large = l + 1 < heapSize && heap[l+1] > heap[l] ? l+1 : l;
            large = heap[i] > heap[large] ? i : large;
            if(large == i) break;
            swap(heap, i, large);
            i = large;
            l = 2 * i + 1;
        }
        return res;
    }

    //判断小根堆是否为空
    public static boolean isEmpty(){
        return heapSize == 0;
    }

    public static void topoSort(){
        int fill = 0;
        for(int i = 1;i <= n;i++){
            if(indegree[i] == 0) push(i);
        }
        while (!isEmpty()) {
            int cur = pop();
            ans[fill++] = cur;
            for(int ei = head[cur];ei != 0;ei = next[ei]){
                if(--indegree[to[ei]] == 0){
                    push(to[ei]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int k = 0;
        while (k++ < 1 && in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)in.nval;
            in.nextToken();
            m = (int)in.nval;
            build(n);
            for(int i = 0,from,to;i < m;i++){
                in.nextToken();
                from = (int)in.nval;
                in.nextToken();
                to = (int) in.nval;
                addEdge(from, to);
                indegree[to]++;
            }
            topoSort();
			for (int i = 0; i < n - 1; i++) {
				out.print(ans[i] + " ");
			}
			out.println(ans[n - 1]);
            
        }
        out.flush();
        out.close();
        br.close();
    }
    
}
