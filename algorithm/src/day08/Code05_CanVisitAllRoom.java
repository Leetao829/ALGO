package day08;

import java.util.Arrays;
import java.util.List;

/**
 * 拜访房间，就是直接写递归了，在递归地过程中是可以进行剪枝的
 * https://leetcode.cn/problems/keys-and-rooms/description/
 */
public class Code05_CanVisitAllRoom {
    public static boolean[] can = new boolean[1001];
    public static int n;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        n = rooms.size();
        Arrays.fill(can, 0,n,false);
        dfs(rooms, 0);
        for(int i = 0;i < n;i++){
            if(!can[i]) return false;
        }
        return true;
    }
    public static void dfs(List<List<Integer>> rooms,int i){
        if(can[i]) return;
        can[i] = true;
        for(int room : rooms.get(i)){
            dfs(rooms, room);
        }
    }
}
