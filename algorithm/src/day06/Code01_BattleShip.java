package day06;

public class Code01_BattleShip {
    //
    public static int ans;
    public static int[] d = {-1,0,1,0,-1};
    public int countBattleships(char[][] board) {
        ans = 0;
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board[0].length;j++){
                if(board[i][j] == 'X'){
                    ans++;
                    dfs(board,i,j);
                }
            }
        }
        return ans;
    }
    public static void dfs(char[][] board,int x,int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '.') return;
        board[x][y] = '.';
        for(int k = 0,nx,ny;k < 4;k++){
            nx = x + d[k];
            ny = y + d[k+1];
            dfs(board,nx,ny);
        }

    }
}
