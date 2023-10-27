import java.util.Arrays;

public class Code06_CheckingExistsEdge {

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int m = edgeList.length;
        int k = queries.length;
        Arrays.sort(edgeList,(a,b)->a[2]-b[2]);
        build(n);
        boolean[] ans = new boolean[k];//创建k次查询的答案
        //下面将每次查询与最后填入的结果相对应
        for(int i = 0;i < k;i++){
            questions[i][0] = queries[i][0];
            questions[i][1] = queries[i][1];
            questions[i][2] = queries[i][2];
            questions[i][3] = i;
        }
        Arrays.sort(questions,0,k,(a,b)->a[2]-b[2]);
        for(int i = 0,j = 0;i < k;i++){
            for(;j < m && edgeList[j][2] < questions[i][2];j++){
                union(edgeList[j][0], edgeList[j][1]);
            }
            ans[questions[i][3]] = isSameSet(questions[i][0], questions[i][1]);

        }
        return ans;
    }

    //最小生成树的有时候用好了还是很强的,这一题其实只是一道并查集的题
    public static int MAXN = 100005;
    public static int[] father = new int[MAXN];
    public static int[][] questions = new int[MAXN][4];

    public static void build(int n){
        for(int i = 0;i < n;i++){
            father[i] = i;
        }
    }
    public static int find(int x){
        if(x != father[x]){
            father[x] = find(father[x]);
        }
        return father[x];
    }
    public static boolean isSameSet(int x,int y){
        return find(x) == find(y);
    }
    public static void union(int x,int y){
        father[find(x)] = find(y);
    }
    
}
