package dessert;
/*
 * 其实我们可以发现，我们能用深度优先实现的操作，往往也能够使用广搜解决问题
 * 这方面我还是很薄弱的
 * 在广度优先遍历过程中使用队列的时候，我们最好还是使用自己创建的队列，主要因为我记不住api
 */
public class Code03_SuroundField {
    public static int MAXN = 1000;
    public static int[][] queue = new int[MAXN][2];
    public static int[] d = {-1,0,1,0,-1};
    public static int l,r;
    public void solve(char[][] board) {
        l = r = 0;
        int n = board.length;
        int m = board[0].length;
        for(int i = 0;i < n;i++){
            if(board[i][0] == 'O') {
                queue[r][0] = i;
                queue[r++][1] = 0;
            }
            if(board[i][m-1] == 'O'){
                queue[r][0] = i;
                queue[r++][1] = m-1;
            }
        }
        for(int i = 1;i < m-1;i++){
            if(board[0][i] == 'O') {
                queue[r][0] = 0;
                queue[r++][1] = i;
            }
            if(board[n-1][i] == 'O'){
                queue[r][0] = n-1;
                queue[r++][1] = i;
            }
        }
        //广搜
        while(l < r){
            //可以一下弹出所有元素
            int size = r - l;
            for(int i = 0;i < size;i++){
                int x = queue[l][0];
                int y = queue[l++][1];
                board[x][y] = 'A';
                for(int k = 0,dx,dy;k < 4;k++){
                    dx = x + d[k];
                    dy = y + d[k+1];
                    if(dx < 0 || dx > n-1 || dy < 0 || dy > m-1 || board[dx][dy] != 'O') continue;
                    //注意：相同的位置可能会重复出现，在这个问题中不会产生影响，因为不涉及到统计信息
                    queue[r][0] = dx;
                    queue[r++][1] = dy;
                }
            }
        }
        for(int i = 0;i < n;i++){
            for(int j = 0;j < m;j++){
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == 'A') board[i][j] = 'O';
            }
        }

    }

}
